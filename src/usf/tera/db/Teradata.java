package usf.tera.db;

import usf.tera.reflect.ReflectFactory.Env;

public class Teradata implements Database {
	
	private static final String URL_TEMPLATE = "jdbc:teradata://%s/database=%s,dbs_port=%d,tmode=tera,charset=utf8,%s";
	
	@Override
	public String makeURL(Env env) {
		return String.format(URL_TEMPLATE, env.getHost(), env.getSchema(), env.getPort(), env.getParams());
	}
	@Override
	public String getDriver() {
		return "com.teradata.jdbc.TeraDriver";
	};
}