package usf.java.sqlreflect.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.Runnable;
import usf.java.sqlreflect.sql.SqlQuery;
import usf.java.sqlreflect.sql.entry.Callable;
import usf.java.sqlreflect.sql.entry.Macro;
import usf.java.sqlreflect.sql.entry.Procedure;

public class SimpleSqlParser implements SqlParser {
	
	private static final String FUNCTION_PATTERN = "(?i)^(call|exec|execute)\\s*(\\w+)\\.(\\w+)\\s*\\((.+)\\)$";
	
	public SimpleSqlParser(){

	}
	
	@Override
	public Callable parseCallable(String sql) {
		Callable callable = null;
		if(sql.matches(FUNCTION_PATTERN)){
			Pattern p = Pattern.compile(FUNCTION_PATTERN);
			Matcher m = p.matcher(sql.trim());
			if(m.matches()){
				callable = m.group(1).toLowerCase().equals("call") ? new Procedure(sql) : new Macro(sql);
				callable.setDatabaseName(m.group(2)); 
				callable.setName(m.group(3));
				callable.setParameters(m.group(4).split("\\s*,\\s*")); //TODO "," 
			}
		}
		return callable;
	}
	
	@Override
	public SqlQuery parseQuery(String sql) {
		return new SqlQuery(sql);
	}
	
	@Override
	public Runnable parseSQL(String sql) {
		Runnable obj = parseCallable(sql);
		if(Utils.isNull(obj)) obj = parseQuery(sql);
		return obj;
	}

}
