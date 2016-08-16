package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sqlreflect.connection.User;

public interface ConnectionProvider {
	
	Connection getConnection(User user) throws SQLException;
	
	void release(Connection cnx);
	
}
