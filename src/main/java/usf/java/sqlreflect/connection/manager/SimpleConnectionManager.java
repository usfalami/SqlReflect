package usf.java.sqlreflect.connection.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.parser.SimpleSqlParser;
import usf.java.sqlreflect.parser.SqlParser;

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
		if(!isValid())
			connection = cp.getConnection();
	}

	public Connection getConnection() throws SQLException {
		if(connection == null || connection.isClosed())
			throw new SQLException("Canot execute this operation on closed connection");
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
	public void close() throws SQLException {
		cp.release(connection);
	}
	
	@Override
	public boolean isValid() {
		boolean valid = false;
		try {
			getConnection();
			valid = true;
		} catch (Exception e) {}
		return valid;
	}
	
	@Override
	public Statement buildStatement(Query query, Arguments args) throws SQLException {
		Connection cnx = getConnection();
		if(args == null || args.isEmpty()) 
			return cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		else{
			PreparedStatement ps = cnx.prepareStatement(query.getSQL(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for(int i=0; i<args.get().length; i++)
				ps.setObject(i+1, args.get()[i]);
			return ps;
		}
	}
	
	@Override
	public ResultSet executeQuery(Statement stmt, String query) throws SQLException {
		return stmt instanceof PreparedStatement ? ((PreparedStatement)stmt).executeQuery() : stmt.executeQuery(query);
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
