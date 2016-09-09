package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sqlreflect.server.Server;

public class SingleConnectionProvider implements ConnectionProvider {

	protected ConnectionProvider cp;
	protected Connection cnx;
	
	public SingleConnectionProvider(ConnectionProvider cp) {
		this.cp = cp;
	}
	
	@Override
	public synchronized Connection getConnection() throws SQLException {
		if(cnx == null || cnx.isClosed())
			cnx = cp.getConnection();
		return cnx;
	}

	@Override
	public void release(Connection cnx) {
		// do not close cnx
	}
	
	@Override
	public Server getServer() {
		return cp == null ? null : cp.getServer();
	}

}
