package usf.java.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import usf.java.sql.db.Env;
import usf.java.sql.db.Server;
import usf.java.sql.db.User;
import usf.java.sql.db.field.SQL;

public class SimpleConnectionManager implements ConnectionManager {

	protected Server server;
	protected User user;
	protected Env env;
	
	public SimpleConnectionManager(Server db, Env env, User user) {
		this.server = db;
		this.env = env;
		this.user = user;
	}

	
	@Override
	public void configure() throws ClassNotFoundException{
		Class.forName(server.getDriver());
	}
	
	@Override	
	public Connection newConnection() throws SQLException{
		return DriverManager.getConnection(server.makeURL(env), user.getUser(), user.getPass());
	}
	
	@Override
	public SQL parseSQL(String sql) {
		SQL obj = server.parseFunction(sql);
		if(obj == null) obj = server.parseQuery(sql);
		return obj;
	}
	
	@Override
	public void closeConnection(Connection cnx) throws SQLException {
		if(cnx==null)return;
		cnx.close();
	}
	
	@Override
	public Server getServer() {
		return server;
	}
	
}
