package usf.java.sql.core.connection.manager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.transcation.TransactionManager;
import usf.java.sql.core.parser.SqlParser;

public interface ConnectionManager {

	Connection getConnection() throws SQLException;
	
	Statement buildStatement(Connection cnx, String sql, Serializable... parameters) throws SQLException;
	
	ResultSet executeQuery(Statement stmt, String query) throws SQLException;

	TransactionManager getTransactionManager();
	
	SqlParser getSqlParser();
	
	void close(Connection cnx);
	
	void close(Statement stmt);
	
	void close(ResultSet rs);
	
}
