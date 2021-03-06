package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.server.Env;
import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.server.User;

public class SimpleConnectionProvider implements ConnectionProvider {

	private String url;
	private User user;
	
	public SimpleConnectionProvider(Server server, Env env, User user) {
		this.url = server.getURL(env);
		this.user = user;
	}
	
	public SimpleConnectionProvider(Server server, Properties properties) {
		this(server, new Env(properties), new User(properties));
	}
	
	@Override
	public synchronized Connection getConnection() throws SQLException {
		return Utils.isEmptyUser(user) ? 
				DriverManager.getConnection(url) : 
				DriverManager.getConnection(url, user.getLogin(), user.getPass());
	}
	
}
