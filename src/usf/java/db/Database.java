package usf.java.db;

import java.io.Serializable;

import usf.java.field.Macro;
import usf.java.field.Parameter;
import usf.java.field.Procedure;
import usf.java.field.Query;

public interface Database {

	String getDriver();
	
	String makeURL(Env env);
	
	Macro parseMacro(String sql, Serializable... parameters);
	
	Procedure parseProcedure(String sql, Serializable... parameters);
	
	Query parseQuery(String sql, Serializable... parameters);
	
	
	public static class Utils {
		
		public static final Parameter[] buildParameters(String[] params){
			Parameter[] paramerter = new Parameter[params.length];
			for(int i=0; i<params.length; i++) 
				paramerter[i] = new Parameter(i, params[i]);
			return paramerter;
		}
		
	}
	
}
