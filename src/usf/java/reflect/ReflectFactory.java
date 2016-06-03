package usf.java.reflect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import usf.java.adapter.Adapter;
import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.db.User;
import usf.java.field.SQL;

public class ReflectFactory {

	protected Database db;
	protected User user;
	protected Env env;
	
	
	private ReflectFactory(Database db, Env env, User user) {
		this.db = db;
		this.env = env;
		this.user = user;
	}
	
	public <E extends Adapter, T extends AbstractReflect<E>> T get(Class<T> clazz, E adapter) throws SQLException, InstantiationException, IllegalAccessException {
		T c = clazz.newInstance();
		c.setReflectFactory(this);
		c.setAdapter(adapter);
		return c;
	}
	
	public Connection newConnection() throws SQLException{
		return DriverManager.getConnection(db.makeURL(env), user.getUser(), user.getPass());
	}
	public Env getEnv() {
		return env;
	}
	public Database getDatabase() {
		return db;
	}
	
	public static final ReflectFactory get(Database db, Env env, User user){
		return new ReflectFactory(db, env, user);
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
