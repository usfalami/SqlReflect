package usf.java.db;

import usf.java.field.Macro;
import usf.java.field.Procedure;
import usf.java.field.Query;

public interface Database {

	String getDriver();
	
	String makeURL(Env env);
	
	Macro parseMacro(String sql);
	
	Procedure parseProcedure(String sql);
	
	Query parseQuery(String sql);
	
}
