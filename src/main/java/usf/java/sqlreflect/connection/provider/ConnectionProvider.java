package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sqlreflect.server.Server;

public interface ConnectionProvider {
	
	Connection getConnection() throws SQLException;
	
	void release(Connection cnx);
	
	Server getServer();
	
}
