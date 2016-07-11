package usf.java.sql.core.connection.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import usf.java.sql.core.field.Env;
import usf.java.sql.core.field.User;
import usf.java.sql.core.server.Server;

public class SimpleConnectionProvider implements ConnectionProvider {

	protected String url;
	
	public SimpleConnectionProvider(Server server, Env env) {
		this.url = server.makeURL(env);
	}
	
	@Override
	public Connection getConnection(User user) throws SQLException {
		return user == null ? null : DriverManager.getConnection(url, user.getLogin(), user.getPass());
	}

}
