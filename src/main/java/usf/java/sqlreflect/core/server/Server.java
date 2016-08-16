package usf.java.sqlreflect.core.server;

import usf.java.sqlreflect.core.field.Callable;
import usf.java.sqlreflect.core.field.Env;
import usf.java.sqlreflect.core.field.SqlQuery;

public interface Server {

	String getDriver();
	
	String buildURL(Env env);
	
	Callable parseCallable(String sql);
	
	SqlQuery parseQuery(String sql);
	
}
