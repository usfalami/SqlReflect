package usf.java.sqlreflect.reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;

public interface Reflector<T> {
	
	void run(Adapter<T> adapter) throws Exception;
	
	List<T> run() throws Exception;
	
	
	
	public static class Utils {
		
		public static boolean isEmpty(String string) {
			return string == null || string.isEmpty();
		}
		
		public static int sum(int ... values) {
			if(values == null) return 0;
			int sum = 0;
			for(int v : values)
				sum += v;
			return sum;
		}
		
		public static final String[] columnNames(ResultSet rs) throws SQLException {
			ResultSetMetaData rm = rs.getMetaData();
			String[] columns = new String[rm.getColumnCount()];
			for(int i=0; i<columns.length; i++)
				columns[i] = rm.getColumnName(i+1);
			return columns;
		}
		
	}
	
}