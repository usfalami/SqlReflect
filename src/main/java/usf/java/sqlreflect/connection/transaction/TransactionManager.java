package usf.java.sqlreflect.connection.transaction;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.sql.Parameters;

public interface TransactionManager extends ConnectionManager {
	
	void startTransaction() throws SQLException;
	
	void endTransaction() throws SQLException;

	void commit() throws SQLException;
	
	void rollback();

	boolean isTransacting();	

	Statement buildBatch(Query... queries) throws SQLException;
	
	Statement buildBatch(Query query, Parameters... args) throws SQLException;

	int executeUpdate(Statement stmt, Query query) throws SQLException;

}
