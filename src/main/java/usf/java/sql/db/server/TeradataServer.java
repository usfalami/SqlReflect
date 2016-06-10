package usf.java.sql.db.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usf.java.sql.db.Env;
import usf.java.sql.db.Server;
import usf.java.sql.db.field.Callable;
import usf.java.sql.db.field.Query;

public class TeradataServer implements Server {
	
	private static final String URL_TEMPLATE = "jdbc:teradata://%s/database=%s,dbs_port=%d,%s";

	private static final String FUNCTION_PATTERN = "(?i)^(call|exec|execute)\\s*(\\w+)\\.(\\w+)\\s*\\((.+)\\)$";
	
	@Override
	public String makeURL(Env env) {
		return String.format(URL_TEMPLATE, env.getHost(), env.getDatabase(), env.getPort(), env.getParams());
	}
	@Override
	public String getDriver() {
		return "com.teradata.jdbc.TeraDriver";
	};
	
	@Override
	public Callable parseFunction(String sql) {
		Callable funct = null;
		if(sql.matches(FUNCTION_PATTERN)){
			Pattern p = Pattern.compile(FUNCTION_PATTERN);
			Matcher m = p.matcher(sql.trim());
			if(m.matches()){
				funct = new Callable(sql);
				funct.setType(m.group(1).toLowerCase().equals("call")?"PROCEDURE":"MACRO");
				funct.setDatabase(m.group(2)); 
				funct.setName(m.group(3));
				funct.setParameters(m.group(4).split("\\s*,\\s*"));
			}
		}
		return funct;
	}
	
	@Override
	public Query parseQuery(String sql) {
		return new Query(sql);
	}
	
}