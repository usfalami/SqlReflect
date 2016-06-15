package usf.java.sql.core.server;

import usf.java.sql.core.field.Function;
import usf.java.sql.core.field.Env;
import usf.java.sql.core.field.Query;

public interface Server {

	String getDriver();
	
	String makeURL(Env env);
	
	Function parseCallable(String sql);
	
	Query parseQuery(String sql);
	
}
