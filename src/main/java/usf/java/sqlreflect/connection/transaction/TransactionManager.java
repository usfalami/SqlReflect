package usf.java.sqlreflect.connection.transaction;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.Parameters;
import usf.java.sqlreflect.sql.Runnable;

public interface TransactionManager extends ConnectionManager {
	
	void startTransaction() throws SQLException;
	
	void endTransaction() throws SQLException;

	void commit() throws SQLException;
	
	void rollback();

	boolean isTransacting();	

	Statement buildBatch(Runnable... queries) throws SQLException;
	
	Statement buildBatch(Runnable query, Parameters... args) throws SQLException;

	int executeUpdate(Statement stmt, Runnable query, Parameter<?>... args) throws SQLException;

}
