package usf.java.sqlreflect.server;

import usf.java.sqlreflect.item.Callable;
import usf.java.sqlreflect.item.SqlQuery;

public interface Server {

	String getDriver();
	
	String buildURL(Env env);
	
	Callable parseCallable(String sql);
	
	SqlQuery parseQuery(String sql);
	
}
