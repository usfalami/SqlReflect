package usf.java.sql.core.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sql.core.connection.User;

public interface ConnectionProvider {
	
	Connection getConnection(User user) throws SQLException;
	
	void release(Connection cnx);
	
}
