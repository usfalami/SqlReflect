package usf.java.sqlreflect.parser;

import usf.java.sqlreflect.sql.Runnable;
import usf.java.sqlreflect.sql.SqlQuery;
import usf.java.sqlreflect.sql.entry.Callable;

public interface SqlParser {
	
	Callable parseCallable(String sql);
	
	SqlQuery parseQuery(String sql);
	
	Runnable parseSQL(String sql);

}
