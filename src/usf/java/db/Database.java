package usf.java.db;

import usf.java.field.Macro;
import usf.java.field.Parameter;
import usf.java.field.Procedure;
import usf.java.field.Query;

public interface Database {

	String getDriver();
	
	String makeURL(Env env);
	
	Macro parseMacro(String sql);
	
	Procedure parseProcedure(String sql);
	
	Query parseQuery(String sql);
	
	
	public static class Utils {
		public static final Parameter[] buildParameters(String[] params){
			Parameter[] paramerter = new Parameter[params.length];
			for(int i=0; i<params.length; i++) 
				paramerter[i] = new Parameter(i, params[i].trim());
			return paramerter;
		}
	}
	
}
