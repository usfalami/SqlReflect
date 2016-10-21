package usf.java.sqlreflect.connection.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;

public class SimpleTransactionManager extends SimpleConnectionManager implements TransactionManager {

	private boolean transact;
	
	public SimpleTransactionManager(ConnectionProvider cp) {
		super(cp);
	}

	@Override
	public void startTransaction() throws SQLException {
		getConnection().setAutoCommit(false);
		transact = true;
	}

	@Override
	public void endTransaction() throws SQLException {
		transact = false; //set transact false before setAutoCommit
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
	public Statement buildBatch(String... queries) throws SQLException {
		if(queries == null || queries.length == 0) throw new SQLException("one query at least");
		Connection cnx = getConnection();
		Statement stmt = cnx.createStatement();
		for(String query : queries)
			stmt.addBatch(query);
		return stmt;
	}
	@Override
	public <P> Statement buildBatch(String query, Collection<P> argList, Binder<P> binder) throws SQLException {
		Connection cnx = getConnection();
		PreparedStatement ps = cnx.prepareStatement(query);		
		for(P args : argList){
			binder.bindPreparedStatement(ps, args);
			ps.addBatch();
		}
		return ps;
	}
	
	@Override
	public <P> int executeUpdate(Statement stmt, String query, P args, Binder<P> binder) throws SQLException {
		int result = 0;
		if(stmt instanceof PreparedStatement){
			result = ((PreparedStatement)stmt).executeUpdate();
			if(stmt instanceof PreparedStatement)
				binder.updateOutParameter((CallableStatement)stmt, args);
		}
		else result = stmt.executeUpdate(query);
		return result;
	}
	
}
