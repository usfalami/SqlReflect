package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import usf.java.sqlreflect.connection.User;

public class PoolConnectionProvider implements ConnectionProvider {

	protected DataSource ds;

	protected PoolConnectionProvider() {

	}

	public PoolConnectionProvider(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Connection getConnection(User user) throws SQLException {
		return user == null ? null : ds.getConnection();
		//		return user == null ? null : ds.getConnection(user.getLogin(), user.getPass());
	}

	@Override
	public void release(Connection cnx) {
		if(cnx != null)
			try {
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
