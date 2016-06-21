package usf.java.sql.core.connection;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sql.core.field.User;

public class SingleConnectionManager extends SimpleConnectionManager {
	
	private Connection cnx;
	
	public SingleConnectionManager(User user) {
		super(user);
	}
	
	@Override
	public Connection newConnection() throws SQLException {
		if(cnx == null || cnx.isClosed())
			cnx = super.newConnection();
		return cnx;
	}
	
	@Override
	public void close(Connection cnx) throws SQLException {
		//super.CloseConnection(cnx); do not close connection
	}

}
