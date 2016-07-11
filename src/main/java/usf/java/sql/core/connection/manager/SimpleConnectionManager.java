package usf.java.sql.core.connection.manager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.provider.ConnectionProvider;
import usf.java.sql.core.field.User;

public class SimpleConnectionManager implements ConnectionManager {

	protected ConnectionProvider cp;
	protected User user;
	
	public SimpleConnectionManager(ConnectionProvider cp, User user) {
		this.cp = cp;
		this.user = user;
	}

	@Override	
	public Connection getConnection() throws SQLException {
		return cp.getConnection(user);
	}
	
	@Override
	public Statement buildStatement(Connection cnx, String sql, Serializable... parameters) throws SQLException  {
		if(parameters == null || parameters.length==0) 
			return cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		else{
			PreparedStatement ps = cnx.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for(int i=0; i<parameters.length; i++)
				ps.setObject(i+1, parameters[i]);
			return ps;
		}
	}	

	@Override
	public void close(Connection cnx) {
		cp.release(cnx);
	}
	@Override
	public void close(Statement stmt) {
		if(stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	@Override
	public void close(ResultSet rs) {
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
