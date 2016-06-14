package usf.java.sql.core.connection;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sql.core.db.Server;
import usf.java.sql.core.db.field.SQL;

public interface ConnectionManager {

	void configure() throws ClassNotFoundException;
	
	public Connection newConnection() throws SQLException;	
	
	public SQL parseSQL(String sql);

	public void closeConnection(Connection cnx) throws SQLException;
	
	Server getServer();

	
}
