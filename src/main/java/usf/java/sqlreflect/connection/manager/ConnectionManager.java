package usf.java.sqlreflect.connection.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.bender.Binder;
import usf.java.sqlreflect.parser.SqlParser;
import usf.java.sqlreflect.sql.Runnable;

public interface ConnectionManager {
	
	void openConnection() throws SQLException;
	
	Connection getConnection() throws SQLException;
	
	boolean isValid();
	
	void close(Statement stmt);
	
	void close(ResultSet rs);

	void close();

	<P> Statement buildStatement(Runnable query, P args, Binder<P> binder) throws SQLException;

	<P> ResultSet executeQuery(Statement stmt, String query, P args, Binder<P> binder) throws SQLException;
	
	SqlParser getSqlParser();

}
