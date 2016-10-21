package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import usf.java.sqlreflect.server.User;

public class PoolConnectionProvider implements ConnectionProvider {

	private DataSource ds;
	private User user;

	protected PoolConnectionProvider() {

	}

	public PoolConnectionProvider(DataSource ds, User user) {
		this.ds = ds;
		this.user = user;
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {//TODO Check this
	//		return user == null ? ds.getConnection() : ds.getConnection(user.getLogin(), user.getPass());
		return ds.getConnection();
	}
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

}
