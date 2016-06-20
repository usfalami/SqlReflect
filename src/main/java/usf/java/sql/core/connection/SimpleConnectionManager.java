package usf.java.sql.core.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.field.Callable;
import usf.java.sql.core.field.Env;
import usf.java.sql.core.field.User;
import usf.java.sql.core.server.Server;

public class SimpleConnectionManager implements ConnectionManager {

	protected Server server;
	protected User user;
	protected Env env;
	
	public SimpleConnectionManager(Server db, Env env, User user) {
		this.server = db;
		this.env = env;
		this.user = user;
	}

	@Override
	public void configure() throws ClassNotFoundException{
		Class.forName(server.getDriver());
	}
	
	@Override
	public Callable parseSQL(String sql) {
		Callable obj = server.parseCallable(sql);
		if(obj == null) obj = server.parseQuery(sql);
		return obj;
	}
	
	@Override	
	public Connection newConnection() throws SQLException{
		return DriverManager.getConnection(server.makeURL(env), user.getUser(), user.getPass());
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

	@Override
	public Server getServer() {
		return server;
	}

}
