package usf.java.sqlreflect.connection.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.parser.SimpleSqlParser;
import usf.java.sqlreflect.parser.SqlParser;
import usf.java.sqlreflect.sql.Runnable;

public class SimpleConnectionManager implements ConnectionManager {

	private ConnectionProvider cp;
	private Connection connection;
	private SqlParser sqlParser;
	
	public SimpleConnectionManager(ConnectionProvider cp) {
		this.cp = cp;
		this.sqlParser = new SimpleSqlParser(cp.getServer());
	}

	@Override
	public void openConnection() throws SQLException {
		if(!isValid()) connection = cp.getConnection();
	}

	public Connection getConnection() throws SQLException {
		if(!isValid()) throw new SQLException("Canot execute this operation on closed connection");
		return connection;
	}
	
	@Override
	public void close(ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void close(Statement stmt) {
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void close() {
		cp.release(connection);
	}
	
	@Override
	public boolean isValid() {
		boolean valid = false;
		try {
			valid = connection != null && !connection.isClosed();
		} catch (Exception e) {}
		return valid;
	}
	
	@Override
	public <T> Statement buildStatement(Runnable query, T args, Binder<T> binder) throws SQLException {
		Connection cnx = getConnection();
		if(args == null) //TODO : check args.isEmpty 
			return cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		else if(!query.asQuery().toUpperCase().startsWith("\\s*CALL")){//TODO udapte this test
			PreparedStatement ps = cnx.prepareStatement(query.asQuery(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			binder.bindPreparedStatement(ps, args);
			return ps;
		}
		else{
			CallableStatement cs = cnx.prepareCall(query.asQuery(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			binder.bindCallableStatement(cs, args);
			return cs;
		}
	}
	
	@Override
	public <T> ResultSet executeQuery(Statement stmt, String query, T args, Binder<T> binder) throws SQLException {
		ResultSet rs = null;
		if(stmt instanceof PreparedStatement){
			rs = ((PreparedStatement)stmt).executeQuery();
			if(stmt instanceof CallableStatement)
				binder.updateOutParameter((CallableStatement)stmt, args);
		}
		else rs = stmt.executeQuery(query);
		return rs;
	}
	
	@Override
	public SqlParser getSqlParser() {
		return sqlParser;
	}

	//TODO Check this
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.close(); //close current connection
	}
	
}
