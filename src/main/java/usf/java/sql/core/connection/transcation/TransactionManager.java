package usf.java.sql.core.connection.transcation;

import java.sql.SQLException;

public interface TransactionManager {
	
	void startTransaction() throws SQLException;
	
	void endTransaction() throws SQLException;

	void rollback() throws SQLException;

	void close() throws SQLException;

}
