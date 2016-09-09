package usf.java.sqlreflect.connection.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.manager.SimpleConnectionManager;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;

public class SimpleTransactionManager extends SimpleConnectionManager implements TransactionManager {
	
	private boolean transact;

	public SimpleTransactionManager(ConnectionProvider cp) {
		super(cp);
	}

	@Override
	public void startTransaction() throws SQLException {
		openConnection();
		getConnection().setAutoCommit(false);
		transact = true;
	}

	@Override
	public void endTransaction() throws SQLException {
		transact = false; //keep set transact false before setAutoCommit
		getConnection().setAutoCommit(true);
	}
	
	@Override
	public void commit() throws SQLException {
		getConnection().commit();
	}

	@Override
	public void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isTransacting() {
		return transact;
	}

	@Override
	public void close() {
		try {
			if(!isTransacting()) //
				super.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Statement buildBatch(Query... queries) throws SQLException {
		Connection cnx = getConnection();
		Statement stmt = cnx.createStatement();
		for(Query query : queries)
			stmt.addBatch(query.getSQL());
		return stmt;
	}
	@Override
	public Statement buildBatch(Query query, Arguments... argList) throws SQLException {
		Connection cnx = getConnection();
		PreparedStatement pstmt = cnx.prepareStatement(query.getSQL());
		for(Arguments arguments : argList){
			for(int i=0; i<arguments.get().length; i++)
				pstmt.setObject(i+1, arguments.get()[i]);
			pstmt.addBatch();
		}
		return pstmt;
	}
	
	@Override
	public int executeUpdate(Statement stmt, Query query) throws SQLException {
		return stmt instanceof PreparedStatement ? ((PreparedStatement)stmt).executeUpdate() : stmt.executeUpdate(query.getSQL());
	}
	
}
