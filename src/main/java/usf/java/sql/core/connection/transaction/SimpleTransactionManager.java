package usf.java.sql.core.connection.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.manager.SimpleConnectionManager;
import usf.java.sql.core.connection.provider.ConnectionProvider;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.field.User;
import usf.java.sql.core.reflect.Arguments;
import usf.java.sql.core.server.Server;

public class SimpleTransactionManager extends SimpleConnectionManager implements TransactionManager {

	private Connection cnx;
	
	public SimpleTransactionManager(ConnectionProvider cp, Server server, User user) {
		super(cp, server, user);
	}

	@Override
	public Connection getConnection() throws SQLException {
		if(cnx == null || cnx.isClosed()) //
			cnx = super.getConnection();
		System.out.println("Check transaction connection : " + cnx);
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
