package usf.java.db.type;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.field.Macro;
import usf.java.field.Parameter;
import usf.java.field.Procedure;
import usf.java.field.SQL;

public class Teradata implements Database {
	
	private static final String URL_TEMPLATE = "jdbc:teradata://%s/database=%s,dbs_port=%d,tmode=tera,charset=utf8,%s";

	private static final String PROCEDURE_PATTERN = "(?i)^call (\\w+)\\.(\\w+)\\s*\\((.+)\\)$";
	private static final String MACRO_PATTERN = "(?i)^exec (\\w+)\\.(\\w+)\\s*\\((.+)\\)$";
	
	@Override
	public String makeURL(Env env) {
		return String.format(URL_TEMPLATE, env.getHost(), env.getSchema(), env.getPort(), env.getParams());
	}
	@Override
	public String getDriver() {
		return "com.teradata.jdbc.TeraDriver";
	};
	
	@Override
	public SQL build(String sql, Serializable... parameters){
		Pattern p = null;
		SQL res = null;
		if(sql.matches(PROCEDURE_PATTERN)){
			p = Pattern.compile(PROCEDURE_PATTERN);
			res = new Procedure(sql);
		}
		else if(sql.matches(MACRO_PATTERN)){
			p = Pattern.compile(MACRO_PATTERN);
			res = new Macro(sql);
		}//else query !!!
		Matcher m = p.matcher(sql);
		if(m.matches()){
			res.setName(m.group(2));
			res.setSchema(m.group(1)); 
			res.setParameters(build(m.group(3).split(",")));
		}
		return res;
	}
	
	public static final Parameter[] build(String[] params){
		Parameter[] paramerter = new Parameter[params.length];
		for(int i=0; i<params.length; i++) 
			paramerter[i] = new Parameter(i, params[i]);
		return paramerter;
	}
	
}