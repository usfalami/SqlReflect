package usf.java.sql.connection;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sql.db.Server;
import usf.java.sql.db.field.SQL;

public interface ConnectionManager {

	void configure() throws ClassNotFoundException;
	
	public Connection newConnection() throws SQLException;	
	
	public SQL parseSQL(String sql);

	public void closeConnection(Connection cnx) throws SQLException;
	
	Server getServer();

	
}
