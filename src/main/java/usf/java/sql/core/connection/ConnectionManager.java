package usf.java.sql.core.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.field.SQL;
import usf.java.sql.core.server.Server;

public interface ConnectionManager {

	void configure() throws ClassNotFoundException;

	Server getServer();
	
	public Connection newConnection() throws SQLException;	
	
	public SQL parseSQL(String sql);

	public void close(Connection cnx) throws SQLException;
	
	public void close(Statement stmt) throws SQLException;
	
	public void close(ResultSet rs) throws SQLException;
	
}
