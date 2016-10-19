package usf.java.sqlreflect.reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;

public interface Reflector<R> {
	
	void run(Adapter<R> adapter) throws Exception;
	
	List<R> run() throws Exception;
	
	
	public static class Utils {
		
		public static boolean isEmpty(String arg) {
			return arg == null || arg.isEmpty();
		}
		
		public static <P> boolean isEmpty(P... args) {
			return args == null || args.length == 0;
		}
		
		public static boolean isEmpty(Collection<?> arg) {
			return arg == null || arg.isEmpty();
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