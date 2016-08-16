package usf.java.sqlreflect.connection.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.User;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.parser.SimpleSqlParser;
import usf.java.sqlreflect.parser.SqlParser;
import usf.java.sqlreflect.reflect.Arguments;
import usf.java.sqlreflect.server.Server;

public class SimpleConnectionManager implements ConnectionManager {

	private ConnectionProvider cp;
	private SqlParser parser;
	private User user;
	
	public SimpleConnectionManager(ConnectionProvider cp, Server server, User user) {
		this.parser = new SimpleSqlParser(server);
		this.cp = cp;
		this.user = user;
	}

	@Override	
	public Connection getConnection() throws SQLException {
		return cp.getConnection(user);
	}
	
	@Override
	public Statement buildStatement(Connection cnx, Query query, Arguments args) throws SQLException  {
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
		return parser;
	}
	
	@Override
	public void close(Connection cnx) {
		cp.release(cnx);
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
	public void close(ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
