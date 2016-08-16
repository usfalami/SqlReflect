package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import usf.java.sqlreflect.connection.User;
import usf.java.sqlreflect.field.Env;
import usf.java.sqlreflect.server.Server;

public class SimpleConnectionProvider implements ConnectionProvider {

	protected String url;
	
	public SimpleConnectionProvider(Server server, Env env) {
		this.url = server.buildURL(env);
	}
	
	@Override
	public synchronized Connection getConnection(User user) throws SQLException {
		return user == null ? null : DriverManager.getConnection(url, user.getLogin(), user.getPass());
	}
	
	@Override
	public void release(Connection cnx) {
		try {
			if(cnx != null && !cnx.isClosed())
				cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
