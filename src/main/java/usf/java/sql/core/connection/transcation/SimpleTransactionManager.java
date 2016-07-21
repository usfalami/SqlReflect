package usf.java.sql.core.connection.transcation;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sql.core.connection.manager.ConnectionManager;

public class SimpleTransactionManager implements TransactionManager {
	
	private ConnectionManager cm;
	private Connection cnx;
	
	public SimpleTransactionManager(ConnectionManager cm){
		 this.cm = cm;
	}

	@Override
	public void startTransaction() throws SQLException {
		if(cnx != null) return; //throw exception already opened
		cnx = cm.getConnection();
		cnx.setAutoCommit(false);
	}

	@Override
	public void endTransaction() throws SQLException {
		if(cnx == null || cnx.isClosed()) return; //throw exception no transaction
		cnx.commit();
		cnx.setAutoCommit(true);
	}

	@Override
	public void rollback() throws SQLException{
		if(cnx == null || cnx.isClosed()) return; //throw exception no transaction
		cnx.rollback();
	}

	@Override
	public void close() throws SQLException {
		cm.close(cnx);
		cnx = null;
	}

}
