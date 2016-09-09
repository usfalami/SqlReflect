package usf.java.sqlreflect.connection.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.parser.SqlParser;

public interface ConnectionManager {
	
	void openConnection() throws SQLException;
	
	Connection getConnection() throws SQLException;
	
	boolean isValid();
	
	void close(Statement stmt);
	
	void close(ResultSet rs);

	void close();

	Statement buildStatement(Query query, Arguments args) throws SQLException;
	
	ResultSet executeQuery(Statement stmt, String query) throws SQLException;
	
	SqlParser getSqlParser();

}
