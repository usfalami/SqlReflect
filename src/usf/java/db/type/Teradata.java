package usf.java.db.type;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.field.Macro;
import usf.java.field.Procedure;
import usf.java.field.Query;

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
	public Macro parseMacro(String sql) {
		Macro macro = null;
		if(sql.matches(MACRO_PATTERN)){
			Pattern p = Pattern.compile(MACRO_PATTERN);
			Matcher m = p.matcher(sql);
			if(m.matches()){
				macro = new Macro(sql);
				macro.setName(m.group(2));
				macro.setSchema(m.group(1)); 
				macro.setParameters(m.group(3).split("\\s*,\\s*"));
			}
		}
		return macro;
	}
	@Override
	public Procedure parseProcedure(String sql) {
		Procedure proc = null;
		if(sql.matches(PROCEDURE_PATTERN)){
			Pattern p = Pattern.compile(PROCEDURE_PATTERN);
			Matcher m = p.matcher(sql);
			if(m.matches()){
				proc = new Procedure(sql);
				proc.setName(m.group(2));
				proc.setSchema(m.group(1)); 
				proc.setParameters(m.group(3).split("\\s*,\\s*"));
			}
		}
		return proc;
	}
	@Override
	public Query parseQuery(String sql) {
		return null;
	}
	
}