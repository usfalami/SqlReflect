package usf.java.sqlreflect.connection.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.server.Server;

public class SimpleTransactionManager extends SimpleConnectionManager implements TransactionManager {

	private boolean defaultAutoCommit, transacting;
	
	public SimpleTransactionManager(ConnectionProvider cp, Server server) {
		super(cp, server);
	}
	
	@Override
	public void startTransaction() throws SQLException {
		Connection cnx = getConnection();
		this.defaultAutoCommit = cnx.getAutoCommit();
		if(!defaultAutoCommit) cnx.setAutoCommit(false);
		this.transacting = true;
	}

	@Override
	public void endTransaction() throws SQLException {
		this.transacting = false; //set transact false before setAutoCommit
		if(!defaultAutoCommit) getConnection().setAutoCommit(true);
	}

	@Override
	public boolean isTransacting() throws SQLException {
		return this.transacting && !getConnection().getAutoCommit();
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
	public void close() {
		if(!this.transacting) //
			super.close();
	}

	@Override
	public Statement buildBatch(String... queries) throws SQLException {
		if(Utils.isEmptyArray(queries)) throw new SQLException("one query at least");
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
			binder.bind(ps, args);
			ps.addBatch();
		}
		return ps;
	}
	
	@Override
	public <P> int executeUpdate(Statement stmt, String query, P args, Binder<P> binder) throws SQLException {
		int result = 0;
		result = stmt instanceof PreparedStatement ? ((PreparedStatement)stmt).executeUpdate() : stmt.executeUpdate(query);
		if(binder != null)
			binder.post(stmt, args);
		return result;
	}
	
}
