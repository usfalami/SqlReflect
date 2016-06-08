package usf.java.sql.db;

import java.util.ArrayList;
import java.util.List;

import usf.java.sql.db.field.Function;
import usf.java.sql.db.field.Query;

public interface Server {

	String getDriver();
	
	String makeURL(Env env);
	
	Function parseFunction(String sql);
	
	Query parseQuery(String sql);
	
	public static class Utils {
		
		public Integer[] bindableParameterIndexs(String... parameters) {
			if(parameters == null) return null;
			List<Integer> list = new ArrayList<Integer>();
			for(int i=0; i<parameters.length; i++)
				if("?".equals(parameters[i])) list.add(i);
			return list.toArray(new Integer[list.size()]);
		}
		
	}
	
}
