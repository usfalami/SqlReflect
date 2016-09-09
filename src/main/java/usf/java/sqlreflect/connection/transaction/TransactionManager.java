package usf.java.sqlreflect.connection.transaction;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;

public interface TransactionManager extends ConnectionManager {
	
	void startTransaction() throws SQLException;
	
	void endTransaction() throws SQLException;

	void commit() throws SQLException;
	
	void rollback();

	boolean isTransacting();	

	Statement buildBatch(Query... queries) throws SQLException;
	
	Statement buildBatch(Query query, Arguments... args) throws SQLException;

	int executeUpdate(Statement stmt, Query query) throws SQLException;

}
