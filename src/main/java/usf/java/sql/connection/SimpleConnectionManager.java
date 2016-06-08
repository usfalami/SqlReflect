package usf.java.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import usf.java.sql.db.Server;
import usf.java.sql.db.Env;
import usf.java.sql.db.User;
import usf.java.sql.db.field.SQL;

public class SimpleConnectionManager implements ConnectionManager {

	protected Server db;
	protected User user;
	protected Env env;
	
	public SimpleConnectionManager(Server db, Env env, User user) {
		this.db = db;
		this.env = env;
		this.user = user;
	}

	
	@Override
	public void configure() throws ClassNotFoundException{
		Class.forName(db.getDriver());
	}
	
	@Override	
	public Connection newConnection() throws SQLException{
		return DriverManager.getConnection(db.makeURL(env), user.getUser(), user.getPass());
	}
	
	@Override
	public SQL parseSQL(String sql) {
		SQL obj = db.parseProcedure(sql);
		if(obj == null) obj = db.parseMacro(sql);
		if(obj == null) obj = db.parseQuery(sql);
		return obj;
	}
	
	@Override
	public void closeConnection(Connection cnx) throws SQLException {
		if(cnx==null)return;
		cnx.close();
	}
	
}
