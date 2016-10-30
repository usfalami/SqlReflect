package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.server.User;

public class DataSourceConnectionProvider implements ConnectionProvider {

	private DataSource ds;
	private User user;

	protected DataSourceConnectionProvider() {

	}

	public DataSourceConnectionProvider(DataSource ds, User user) {
		this.ds = ds;
		this.user = user;
	}

	@Override
	public synchronized Connection getConnection() throws SQLException {
		return Utils.isEmptyUser(user) ? 
				ds.getConnection() : 
				ds.getConnection(user.getLogin(), user.getPass());
	}
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

}
