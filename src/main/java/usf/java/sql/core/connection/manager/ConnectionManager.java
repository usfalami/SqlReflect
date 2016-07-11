package usf.java.sql.core.connection.manager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface ConnectionManager {

	Connection getConnection() throws SQLException;
	
	Statement buildStatement(Connection cnx, String sql, Serializable... parameters) throws SQLException;

	void close(Connection cnx);
	
	void close(Statement stmt);
	
	void close(ResultSet rs);
	
}
