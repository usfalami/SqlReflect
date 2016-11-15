package usf.java.sqlreflect.reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import usf.java.sqlreflect.server.User;

public class Utils {

	public static boolean isEmptyString(String arg) {
		return arg == null || arg.isEmpty();
	}
	public static <P> boolean isEmptyArray(P... args) {
		return args == null || args.length == 0;
	}
	public static boolean isEmptyCollection(Collection<?> arg) {
		return arg == null || arg.isEmpty();
	}
	public static boolean isEmptyMap(Map<?,?> arg) {
		return arg == null || arg.isEmpty();
	}
	public static boolean isEmptyUser(User user) {
		return user == null || isEmptyString(user.getLogin()); //valid login at least
	}
	
	public static Integer[] convert(int... values){
		if(values == null) return null;
		Integer[] arr = new Integer[values.length];
		for(int i=0; i<values.length; i++)
			arr[i] = values[i];
		return arr;
	} 
	
	public static final String[] columnNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rm = rs.getMetaData();
		String[] columns = new String[rm.getColumnCount()];
		for(int i=0; i<columns.length; i++)
			columns[i] = rm.getColumnName(i+1);
		return columns;
	}
	
	public static boolean sameClass(Object o, Class<?> c) {
		return c.getName().equals(o.getClass().getName());
	}
	
}
