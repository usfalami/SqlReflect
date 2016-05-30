package usf.tera.reflect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import usf.tera.adapter.Adapter;
import usf.tera.db.Database;

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
	
	public static class User {
		private String user, pass;
		public User(String login, String pass) {
			this.user = login;
			this.pass = pass;
		}
		public String getUser() {return user;}
		public String getPass() {return pass;}
	}
	
	public static class Env {
		
		protected String host;
		protected String schema;
		protected int port;
		protected String params;

		public Env(String host, String schema, int port) {
			this(host, schema, port, "");
		}
		public Env(String host, String schema, int port, String params) {
			this.host = host;
			this.schema = schema;
			this.params = params;
			this.port=port;
		}
		public String getHost() {return host;}
		public String getSchema() {return schema;}
		public int getPort() {return port;}
		public String getParams() {return params;}
	}
	
	
}
