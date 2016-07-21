package usf.java.sql.core.connection.manager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.provider.ConnectionProvider;
import usf.java.sql.core.connection.transcation.SimpleTransactionManager;
import usf.java.sql.core.connection.transcation.TransactionManager;
import usf.java.sql.core.field.User;
import usf.java.sql.core.parser.SimpleSqlParser;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.server.Server;

public class SimpleConnectionManager implements ConnectionManager {

	private ConnectionProvider cp;
	private SqlParser parser;
	private User user;
	
	public SimpleConnectionManager(ConnectionProvider cp, Server server, User user) {
		this.parser = new SimpleSqlParser(server);
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
	public ResultSet executeQuery(Statement stmt, String query) throws SQLException {
		return stmt instanceof Statement ? stmt.executeQuery(query) : ((PreparedStatement)stmt).executeQuery();
	}
	
	@Override
	public TransactionManager getTransactionManager() {
		return new SimpleTransactionManager(this);
	}

	@Override
	public SqlParser getSqlParser() {
		return parser;
	}
	
	@Override
	public void close(Connection cnx) {
		cp.release(cnx);
	}
	@Override
	public void close(Statement stmt) {
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void close(ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
