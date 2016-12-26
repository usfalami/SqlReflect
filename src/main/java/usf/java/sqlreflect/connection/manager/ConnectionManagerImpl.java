package usf.java.sqlreflect.connection.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.server.Server;

public class ConnectionManagerImpl implements ConnectionManager {

	private ConnectionProvider cp;
	private Connection connection;
	private Server server;
	
	public ConnectionManagerImpl(ConnectionProvider cp, Server server) {
		this.cp = cp;
		this.server = server;
	}

	@Override
	public Connection openConnection() throws SQLException {
		if(isClosed()) this.connection = cp.getConnection();
		return connection;
	}

	@Override
	public Connection getConnection() throws SQLException {
		if(isClosed()) throw new SQLException("Canot execute this operation on closed connection");
		return connection;
	}
	
	@Override
	public void close(ResultSet rs) {
		if(Utils.isNotNull(rs)){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void close(Statement stmt) {
		if(Utils.isNotNull(stmt)){
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
			if(Utils.isNotNull(connection))
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{	
			connection = null;
		}
	}
	
	@Override
	public boolean isClosed() throws SQLException {
		return Utils.isNull(connection) || connection.isClosed();
	}
	
	@Override
	public <P> Statement prepare(String query, P args, Binder<P> binder) throws SQLException {
		Connection cnx = getConnection();
		if(Utils.isNull(args, binder)) //TODO : check args.isEmpty 
			return cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		Statement stmt = query.substring(0, 4).toUpperCase().startsWith("CALL") ?
				cnx.prepareCall(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY) : 
				cnx.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		binder.bind(stmt, args);
		return stmt;
	}
	
	@Override
	public <T> ResultSet executeQuery(Statement stmt, String query, T args, Binder<T> binder) throws SQLException {
		ResultSet rs = null;
		rs = stmt instanceof PreparedStatement ? 
				((PreparedStatement)stmt).executeQuery() : stmt.executeQuery(query);
		if(Utils.isNotNull(binder))
			binder.post(stmt, args);
		return rs;
	}

	@Override
	public Server getServer() {
		return server;
	}
	
	//TODO Check this
	@Override
	protected void finalize() throws Throwable {
		this.close(); //close current connection
		super.finalize();
	}
	
}
