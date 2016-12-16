package usf.java.sqlreflect.reflect;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.mapper.filter.DefaultConverter;
import usf.java.sqlreflect.mapper.filter.MapperFilter;
import usf.java.sqlreflect.mapper.filter.ResultConverter;
import usf.java.sqlreflect.server.User;
import usf.java.sqlreflect.writer.TypeWriter;

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
	
	public static boolean isIllegalClass(Class<?> clazz){
		return clazz == null || clazz.isInterface();
	}
	
	public static <T> String[] toString(T... args){
		String[] result = new String[args.length];
		for(int i=0; i<result.length; i++)
			result[i] = args[i].toString();
		return result;
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
	
	public static final Map<String, TypeWriter> columnTypes(ResultSetMetaData rm) throws SQLException {
		int size = rm.getColumnCount();
		Map<String, TypeWriter> map = new HashMap<String, TypeWriter>();
		for(int i=1; i<=size; i++) {
			TypeWriter tw = TypeWriter.writerfor(rm.getColumnClassName(i));
			map.put(rm.getColumnName(i), tw);
		}
		return map;
	}
	
	public static final Map<String, TypeWriter> columnTypes(ResultSetMetaData rm, String... columnNames) throws SQLException {
		if(isEmptyArray(columnNames)) return columnTypes(rm);
		int size = rm.getColumnCount();
		Map<String, TypeWriter> map = new HashMap<String, TypeWriter>();
		for(int i=1; i<=size; i++){
			String columnName = rm.getColumnName(i);
			if(arraySearch(columnName, columnNames) > -1) {
				TypeWriter tw = TypeWriter.writerfor(rm.getColumnClassName(i));
				map.put(rm.getColumnName(i), tw);
			}
		}
		return map;
	}
	
	public static <T extends ResultConverter<?>> Class<?> methodeType(Class<T> clazz) throws NoSuchMethodException, SecurityException {
		return clazz.getDeclaredMethod("convert", Object.class).getReturnType();
	}
	
	public static <T> int arraySearch(T value, T[] array){
		for(int i=0; i<array.length; i++)
			if(array[i].equals(value)) return i;
		return -1;
	}
	
	
	public static final Map<String, TypeWriter> columnTypes(ResultSetMetaData rm, Map<String, MapperFilter> filters) throws SQLException {
		try{
			int size = rm.getColumnCount();
			Map<String, TypeWriter> map = new HashMap<String, TypeWriter>();
			for(int i=1; i<=size; i++){
				String columnName = rm.getColumnName(i);
				MapperFilter filter = filters.get(columnName);
				if(filter != null) {
					ResultConverter<?> conv = filter.getValueConverter();
					if(conv.getClass().equals(DefaultConverter.class)){
						map.put(filter.getPropertyName(), TypeWriter.writerfor(rm.getColumnClassName(i)));
					}
					else {
						Class<?> clazz = methodeType(conv.getClass());
						map.put(filter.getPropertyName(), TypeWriter.writerfor(clazz.getName()));
					}
				}
			}
			return map;
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}
	
	public static boolean sameClass(Object o, Class<?> c) {
		return c.getName().equals(o.getClass().getName());
	}
	
	public static Method findMethod(Object o, String methodName, Object... args) throws Exception {
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
		if(!found) throw new NoSuchMethodException();
		return methods[index-1];
	}
	

	
}
