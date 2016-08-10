package usf.java.sql.core.connection.transaction;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Arguments;

public interface TransactionManager {
	
	void startTransaction() throws SQLException;
	
	void endTransaction() throws SQLException;

	void rollback() throws SQLException;

	void close() throws SQLException;
	

	Statement buildStatement(Query query, Arguments args) throws SQLException;
	
	Statement buildBatch(Query... queries) throws SQLException;
	
	Statement buildBatch(Query query, Arguments... args) throws SQLException;

	int executeUpdate(Statement stmt, Query query) throws SQLException;


}
