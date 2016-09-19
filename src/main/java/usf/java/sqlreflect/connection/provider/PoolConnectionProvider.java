package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.server.User;

public class PoolConnectionProvider implements ConnectionProvider {

	protected DataSource ds;
	private User user;
	private Server server;

	protected PoolConnectionProvider() {

	}

	public PoolConnectionProvider(DataSource ds, Server server, User user) {
		this.ds = ds;
		this.user = user;
		this.server = server;
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {//TODO Check this
	//		return user == null ? ds.getConnection() : ds.getConnection(user.getLogin(), user.getPass());
		return ds.getConnection();
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
	
	@Override
	public Server getServer() {
		return server;
	}

}
