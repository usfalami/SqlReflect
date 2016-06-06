package usf.java.db;

import java.util.ArrayList;
import java.util.List;

import usf.java.field.Macro;
import usf.java.field.Procedure;
import usf.java.field.Query;

public interface Database {

	String getDriver();
	
	String makeURL(Env env);
	
	Macro parseMacro(String sql);
	
	Procedure parseProcedure(String sql);
	
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
