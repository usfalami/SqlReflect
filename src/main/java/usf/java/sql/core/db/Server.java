package usf.java.sql.core.db;

import usf.java.sql.core.db.field.Callable;
import usf.java.sql.core.db.field.Query;

public interface Server {

	String getDriver();
	
	String makeURL(Env env);
	
	Callable parseFunction(String sql);
	
	Query parseQuery(String sql);
	
}
