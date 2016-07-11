package usf.java.sql.core.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import usf.java.sql.core.field.User;

public class PoolConnectionProvider implements ConnectionProvider {

	protected DataSource ds;
	
	protected PoolConnectionProvider() {

	}
	
	public PoolConnectionProvider(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Connection getConnection(User user) throws SQLException {
//		 return user == null ? null : ds.getConnection(user.getLogin(), user.getPass());
		return user == null ? null : ds.getConnection();
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
