package usf.java.sqlreflect.connection.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.User;
import usf.java.sqlreflect.connection.manager.SimpleConnectionManager;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.server.Server;

public class SimpleTransactionManager extends SimpleConnectionManager implements TransactionManager {

	private Connection cnx;
	
	public SimpleTransactionManager(ConnectionProvider cp, Server server, User user) {
		super(cp, server, user);
	}

	@Override
	public Connection getConnection() throws SQLException {
		if(cnx == null || cnx.isClosed()) //
			cnx = super.getConnection();
		System.err.println("Check transaction connexion : " + cnx);
		return this.cnx;
	}
	
	@Override
	public void startTransaction() throws SQLException {
		getConnection().setAutoCommit(false);
	}

	@Override
	public void endTransaction() throws SQLException {
		if(cnx == null || cnx.isClosed()) return; //throw exception no transaction
		try {
			cnx.commit();
		} catch (SQLException e) {
			throw e;			
		}finally {
			cnx.setAutoCommit(true);
		}
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
	public void close(Connection cnx) {
		try {
			if(cnx == null || cnx.isClosed()) return;
			//Important : Close connexion only if there is no transaction
			if(cnx != this.cnx || cnx.getAutoCommit())
				close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		super.close(cnx);
		cnx = null;
		System.err.println("Check connexion closed");
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
	
	@Override
	public int executeUpdate(Statement stmt, Query query) throws SQLException {
		return stmt instanceof PreparedStatement ? ((PreparedStatement)stmt).executeUpdate() : stmt.executeUpdate(query.getSQL());
	}
}
