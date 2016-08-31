package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sqlreflect.server.Env;
import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.server.User;

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
	public void release(Connection cnx) {
		// do not close cnx
	}

}
