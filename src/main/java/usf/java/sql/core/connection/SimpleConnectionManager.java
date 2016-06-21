package usf.java.sql.core.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.field.Env;
import usf.java.sql.core.field.User;
import usf.java.sql.core.server.Server;

public class SimpleConnectionManager implements ConnectionManager {

	protected String url;
	protected User user;
	
	public SimpleConnectionManager(User user) {
		this.user = user;
	}

	@Override
	public void configure(Server server, Env env) throws ClassNotFoundException{
		Class.forName(server.getDriver());
		this.url = server.makeURL(env);
	}
	
	@Override	
	public Connection newConnection() throws SQLException{
		return DriverManager.getConnection(url, user.getUser(), user.getPass());
	}
	
	@Override
	public Statement buildStatement(Connection cnx, String sql, Serializable... parameters) throws SQLException  {
		if(parameters == null || parameters.length ==0) 
			return cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		else{
			PreparedStatement ps = cnx.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if(parameters != null)
				for(int i=0; i<parameters.length; i++)
					ps.setObject(i+1, parameters[i]);
			return ps;
		}
	}	

	@Override
	public void close(Connection cnx) throws SQLException {
		if(cnx == null) return;
		cnx.close();
	}
	@Override
	public void close(Statement stmt) throws SQLException {
		if(stmt == null) return;
		stmt.close();
	}
	@Override
	public void close(ResultSet rs) throws SQLException {
		if(rs == null) return;
		rs.close();
	}

}
