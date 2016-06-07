package usf.java.sql.connection;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.sql.db.Database;
import usf.java.sql.db.Env;
import usf.java.sql.db.User;

public class SingleConnectionManager extends SimpleConnectionManager {
	
	private Connection cnx;
	
	public SingleConnectionManager(Database db, Env env, User user) {
		super(db, env, user);
	}
	
	@Override
	public Connection newConnection() throws SQLException {
		if(cnx==null || cnx.isClosed())
			cnx = super.newConnection();
		return cnx;
	}
	
	@Override
	public void closeConnection(Connection cnx) throws SQLException {
		//super.CloseConnection(cnx);
	}

}
