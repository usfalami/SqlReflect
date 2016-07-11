package usf.java.sql.core.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usf.java.sql.core.field.Env;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.field.Macro;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.field.Query;

public class TeradataServer implements Server {
	
	private static final String URL_TEMPLATE = "jdbc:teradata://%s/database=%s,dbs_port=%d,%s";

	private static final String FUNCTION_PATTERN = "(?i)^(call|exec|execute)\\s*(\\w+)\\.(\\w+)\\s*\\((.+)\\)$";
	
	@Override
	public String buildURL(Env env) {
		return String.format(URL_TEMPLATE, env.getHost(), env.getDatabase(), env.getPort(), env.getParams());
	}
	@Override
	public String getDriver() {
		return "com.teradata.jdbc.TeraDriver";
	}
	
	@Override
	public Function parseCallable(String sql) {
		Function callable = null;
		if(sql.matches(FUNCTION_PATTERN)){
			Pattern p = Pattern.compile(FUNCTION_PATTERN);
			Matcher m = p.matcher(sql.trim());
			if(m.matches()){
				callable = m.group(1).toLowerCase().equals("call") ? new Procedure(sql) : new Macro(sql);
				callable.setDatabase(m.group(2)); 
				callable.setName(m.group(3));
				callable.setParameters(m.group(4).split("\\s*,\\s*"));
			}
		}
		return callable;
	}
	
	@Override
	public Query parseQuery(String sql) {
		return new Query(sql);
	}
	
}