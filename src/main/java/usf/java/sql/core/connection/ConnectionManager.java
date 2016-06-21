package usf.java.sql.core.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.field.Env;
import usf.java.sql.core.server.Server;

public interface ConnectionManager {

	void configure(Server server, Env env) throws ClassNotFoundException;

	Connection newConnection() throws SQLException;
	
	Statement buildStatement(Connection cnx, String sql, Serializable... parameters) throws SQLException;

	void close(Connection cnx) throws SQLException;
	
	void close(Statement stmt) throws SQLException;
	
	void close(ResultSet rs) throws SQLException;
	
}
