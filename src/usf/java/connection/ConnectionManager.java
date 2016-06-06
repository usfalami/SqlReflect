package usf.java.connection;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.field.SQL;

public interface ConnectionManager {

	public Connection newConnection() throws SQLException;	
	
	public SQL parseSQL(String sql);

	public void closeConnection(Connection cnx) throws SQLException;
	
}
