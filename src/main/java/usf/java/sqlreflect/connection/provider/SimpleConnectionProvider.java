package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import usf.java.sqlreflect.server.Env;
import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.server.User;

public class SimpleConnectionProvider implements ConnectionProvider {

	private String url;
	private User user;
	
	public SimpleConnectionProvider(Server server, Env env, User user) {
		this.url = server.buildURL(env);
		this.user = user;
	}
	
	@Override
	public synchronized Connection getConnection() throws SQLException {
		return user == null ? null : DriverManager.getConnection(url, user.getLogin(), user.getPass());
	}
	
}
