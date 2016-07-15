package usf.java.sql.core.server;

import usf.java.sql.core.field.Env;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.field.SqlQuery;

public interface Server {

	String getDriver();
	
	String buildURL(Env env);
	
	Callable parseCallable(String sql);
	
	SqlQuery parseQuery(String sql);
	
}
