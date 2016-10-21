package usf.java.sqlreflect.connection.manager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import usf.java.sqlreflect.binder.Binder;

public interface TransactionManager extends ConnectionManager {
	
	void startTransaction() throws SQLException;
	
	void endTransaction() throws SQLException;

	void commit() throws SQLException;
	
	void rollback();

	boolean isTransacting();	

	Statement buildBatch(String... queries) throws SQLException;

	<P> Statement buildBatch(String query, Collection<P> argsList, Binder<P> binder) throws SQLException;

	<P> int executeUpdate(Statement stmt, String query, P args, Binder<P> binder) throws SQLException;

}
