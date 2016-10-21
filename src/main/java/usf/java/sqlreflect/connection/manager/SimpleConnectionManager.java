package usf.java.sqlreflect.connection.manager;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;

public class SimpleConnectionManager implements ConnectionManager {

	private ConnectionProvider cp;
	private Connection connection;
	
	public SimpleConnectionManager(ConnectionProvider cp) {
		this.cp = cp;
	}

	@Override
	public void openConnection() throws SQLException {
		if(!isValid()) this.connection = cp.getConnection();
	}

	@Override
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
		try {
			if(connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			connection = null;
		}
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
	public <T> Statement buildStatement(String query, T args, Binder<T> binder) throws SQLException {
		Connection cnx = getConnection();
		if(args == null) //TODO : check args.isEmpty 
			return cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		else if(!query.toUpperCase().startsWith("CALL")){//TODO udapte this test
			PreparedStatement ps = cnx.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			binder.bindPreparedStatement(ps, args);
			return ps;
		}
		else{
			CallableStatement cs = cnx.prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

	//TODO Check this
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.close(); //close current connection
	}
	
}
