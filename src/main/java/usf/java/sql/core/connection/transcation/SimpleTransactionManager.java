package usf.java.sql.core.connection.transcation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Arguments;

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
	public void rollback() {
		try {
			if(cnx == null || cnx.isClosed()) return; //throw exception no transaction
				cnx.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws SQLException {
		cm.close(cnx);
		cnx = null;
	}
	
	@Override
	public Statement buildBatch(Query... queries) throws SQLException {
		if(cnx == null || cnx.isClosed()) return null; //throw exception no transaction
		Statement stmt = cnx.createStatement();
		for(Query query : queries)
			stmt.addBatch(query.getSQL());
		return stmt;
	}
	@Override
	public Statement buildBatch(Query query, Arguments... argList) throws SQLException {
		if(cnx == null || cnx.isClosed()) return null; //throw exception no transaction
		PreparedStatement pstmt = cnx.prepareStatement(query.getSQL());
		for(Arguments arguments : argList){
			for(int i=0; i<arguments.get().length; i++)
				pstmt.setObject(i+1, arguments.get()[i]);
			pstmt.addBatch();
		}
		return pstmt;
	}
}
