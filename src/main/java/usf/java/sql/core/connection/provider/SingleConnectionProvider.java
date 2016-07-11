package usf.java.sql.core.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sql.core.field.Env;
import usf.java.sql.core.field.User;
import usf.java.sql.core.server.Server;

public class SingleConnectionProvider extends SimpleConnectionProvider {
	
	protected Connection cnx;
	
	public SingleConnectionProvider(Server server, Env env) {
		super(server, env);
	}
	
	@Override
	public synchronized Connection getConnection(User user) throws SQLException {
		if(cnx == null || cnx.isClosed())
			cnx = super.getConnection(user);
		return cnx;
	}

	@Override
	public void release(Connection cnx) throws SQLException {
		// do not close cnx
	}

}
