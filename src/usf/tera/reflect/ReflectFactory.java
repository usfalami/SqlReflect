package usf.tera.reflect;

import java.sql.DriverManager;
import java.sql.SQLException;

import usf.tera.db.Database;
import usf.tera.reflect.adpter.Adapter;

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
		c.setConnection(DriverManager.getConnection(db.makeURL(env), user.getLogin(), user.getPass()));
		c.setEnvoronnement(env);
		c.setAdapter(adapter);	
		return c;
	}
	
	public static final ReflectFactory get(Database db, Env env, User user){
		return new ReflectFactory(db, env, user);
	}
	
	public static class User {
		private String login, pass;
		public User(String login, String pass) {
			this.login = login;
			this.pass = pass;
		}
		public String getLogin() {return login;}
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
