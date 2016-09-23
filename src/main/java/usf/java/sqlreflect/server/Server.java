package usf.java.sqlreflect.server;

import usf.java.sqlreflect.sql.SqlQuery;
import usf.java.sqlreflect.sql.item.Callable;

public interface Server {

	String getDriver();
	
	String buildURL(Env env);
	
	Callable parseCallable(String sql);
	
	SqlQuery parseQuery(String sql);
	
}
