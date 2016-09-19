package usf.java.sqlreflect.connection.transaction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.manager.SimpleConnectionManager;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.Parameters;
import usf.java.sqlreflect.sql.Query;
import usf.java.sqlreflect.sql.SqlUtils;

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
		if(!isTransacting()) //
			super.close();
	}

	@Override
	public Statement buildBatch(Query... queries) throws SQLException {
		Connection cnx = getConnection();
		Statement stmt = cnx.createStatement();
		SqlUtils.buildBatch(stmt, queries);
		return stmt;
	}
	@Override
	public Statement buildBatch(Query query, Parameters... args) throws SQLException {
		Connection cnx = getConnection();
		PreparedStatement ps = cnx.prepareStatement(query.asQuery());
		SqlUtils.buildBatch(ps, args);
		return ps;
	}
	
	@Override
	public int executeUpdate(Statement stmt, Query query, Parameter<?>... args) throws SQLException {
		int result = 0;
		if(stmt instanceof PreparedStatement){
			result = ((PreparedStatement)stmt).executeUpdate();
			if(stmt instanceof PreparedStatement)
				SqlUtils.updateOutParameter((CallableStatement)stmt, args);
		}
		else result = stmt.executeUpdate(query.asQuery());
		return result;
	}
	
}
