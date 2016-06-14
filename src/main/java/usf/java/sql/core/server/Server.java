package usf.java.sql.core.server;

import usf.java.sql.core.field.Callable;
import usf.java.sql.core.field.Env;
import usf.java.sql.core.field.Query;

public interface Server {

	String getDriver();
	
	String makeURL(Env env);
	
	Callable parseFunction(String sql);
	
	Query parseQuery(String sql);
	
}
