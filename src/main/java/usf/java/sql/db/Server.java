package usf.java.sql.db;

import usf.java.sql.db.field.Callable;
import usf.java.sql.db.field.Query;

public interface Server {

	String getDriver();
	
	String makeURL(Env env);
	
	Callable parseFunction(String sql);
	
	Query parseQuery(String sql);
	
	public static class Utils {
		
		public static int bindableParameterIndexs(String... parameters) {
			int cp=0;
			for(int i=0; i<parameters.length; i++)
				if("?".equals(parameters[i])) cp++;
			return cp;
		}
		
	}
	
}
