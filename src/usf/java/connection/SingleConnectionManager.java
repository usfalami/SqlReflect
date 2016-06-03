package usf.java.connection;

import java.sql.Connection;
import java.sql.SQLException;

import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.db.User;

public class SingleConnectionManager extends ConnectionManager {
	
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
	public void CloseConnection(Connection cnx) throws SQLException {
		//super.CloseConnection(cnx);
	}

}
