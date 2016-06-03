package usf.java.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.db.User;
import usf.java.field.SQL;

public class ConnectionManager {

	protected Database db;
	protected User user;
	protected Env env;
	
	public ConnectionManager(Database db, Env env, User user) {
		this.db = db;
		this.env = env;
		this.user = user;
	}
		
	public Connection newConnection() throws SQLException{
		return DriverManager.getConnection(db.makeURL(env), user.getUser(), user.getPass());
	}
	
	public SQL parseSQL(String sql) {
		SQL obj = db.parseProcedure(sql);
		if(obj == null) obj = db.parseMacro(sql);
		if(obj == null) obj = db.parseQuery(sql);
		return obj;
	}
	
	public void CloseConnection(Connection cnx) throws SQLException {
		if(cnx==null)return;
		cnx.close();
	}
	
}
