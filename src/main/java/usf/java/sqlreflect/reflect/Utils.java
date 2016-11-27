package usf.java.sqlreflect.reflect;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.server.User;

public class Utils {

	public static boolean isNull(Object arg) {
		return arg == null;
	}
	public static boolean isNotNull(Object arg) {
		return arg != null;
	}
	public static boolean isEmptyString(String arg) {
		return isNull(arg) || arg.isEmpty();
	}
	public static <P> boolean isEmptyArray(P[] args) {
		return isNull(args) || args.length == 0;
	}
	public static boolean isEmptyPrimitiveArray(int... args) {
		return isNull(args) || args.length == 0;
	}
	public static boolean isEmptyCollection(Collection<?> arg) {
		return isNull(arg) || arg.isEmpty();
	}
	public static boolean isEmptyMap(Map<?,?> arg) {
		return isNull(arg) || arg.isEmpty();
	}
	public static boolean isEmptyUser(User user) {
		return isNull(user) || isEmptyString(user.getLogin()); //valid login at least
	}
	
	public static <T> boolean isNull(T arg, Binder<T> binder) { //TODO optim test
		return isNull(arg) && isNull(binder);
	}
	public static <T> boolean isIllegalArg(T arg, Binder<T> binder) {
		return isNull(arg) ^ isNull(binder);
	}
	public static <T> boolean isEmpty(Collection<T> args, Binder<T> binder) {
		return isEmptyCollection(args) && isNull(binder);
	}
	public static <T> boolean isIllegalArgs(Collection<T> args, Binder<T> binder) {
		return isEmptyCollection(args) ^ isNull(binder);
	}
	
	public static Integer[] convert(int... values){
		if(isNull(values)) return null;
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
	
	public static Method findMethod(Object o, String methodName, Object... args) throws Error {
		Method[] methods = null; int index = 0; boolean found = false;
		methods = o.getClass().getDeclaredMethods();
		while(index<methods.length && !found){
			if(methods[index].getName().equals(methodName)){
				Class<?>[] clazz = methods[index].getParameterTypes();
				if(clazz.length == args.length){
					int i=0;
					while(i<clazz.length && clazz[i].isInstance(args[i])) i++;
					found = i == clazz.length;
				}
			}
			index++;
		}
		if(!found) throw new NoSuchMethodError();
		return methods[index-1];
	}
	
	public static void main(String[] args) {
		System.out.println(true^false);
	}
}
