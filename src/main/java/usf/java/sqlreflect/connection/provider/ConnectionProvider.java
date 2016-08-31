package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sqlreflect.server.User;

public interface ConnectionProvider {
	
	Connection getConnection(User user) throws SQLException;
	
	void release(Connection cnx);
	
}
