package usf.java.sqlreflect.connection.transaction;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import usf.java.sqlreflect.bender.Binder;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.sql.Runnable;

public interface TransactionManager extends ConnectionManager {
	
	void startTransaction() throws SQLException;
	
	void endTransaction() throws SQLException;

	void commit() throws SQLException;
	
	void rollback();

	boolean isTransacting();	

	Statement buildBatch(Runnable... queries) throws SQLException;

	<P> Statement buildBatch(Runnable query, Collection<P> argsList, Binder<P> binder) throws SQLException;

	<P> int executeUpdate(Statement stmt, Runnable query, P args, Binder<P> binder) throws SQLException;

}
