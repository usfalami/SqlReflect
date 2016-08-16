package usf.java.sqlreflect.connection.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.parser.SqlParser;
import usf.java.sqlreflect.reflect.Arguments;

public interface ConnectionManager {

	Connection getConnection() throws SQLException;
	
	//TODO remove cnx parameter
	Statement buildStatement(Connection cnx, Query query, Arguments args) throws SQLException;
	
	ResultSet executeQuery(Statement stmt, String query) throws SQLException;
	
	SqlParser getSqlParser();
	
	void close(Connection cnx);
	
	void close(Statement stmt);
	
	void close(ResultSet rs);

}
