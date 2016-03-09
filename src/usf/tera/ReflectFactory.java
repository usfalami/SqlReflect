package usf.tera;

import java.sql.DriverManager;
import java.sql.SQLException;

import usf.tera.adpter.Adapter;
import usf.tera.reflect.Reflect;

public class ReflectFactory {

	protected User user;
	protected Env env;

	public ReflectFactory(Env env, User user) {
		this.env = env;
		this.user = user;
	}
	
	public <T extends Reflect> T get(Class<T> clazz, Adapter adapter) throws SQLException, InstantiationException, IllegalAccessException {
		T c = clazz.newInstance();
		c.setConnection(DriverManager.getConnection(env.makeURL(), user.getLogin(), user.getPass()));
		c.setEnvoronnement(env);
		c.setAdapter(adapter);	
		return c;
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
		
		protected static final String URL_TEMPLATE = "jdbc:teradata://%s/database=%s,DBS_PORT=1025,tmode=TERA,charset=UTF8,%s";
		
		protected String host;
		protected String schema;
		protected String params;
		
		public Env(String host, String schema) {
			this(host, schema, "");
		}
		public Env(String host, String schema, String params) {
			this.host = host;
			this.schema = schema;
			this.params = params;
		}
		public String getHost() {return host;}
		public String getSchema() {return schema;}
		public String getParams() {return params;}
		public String makeURL() {return String.format(URL_TEMPLATE, host, schema, params);}
	}
	
	
}
