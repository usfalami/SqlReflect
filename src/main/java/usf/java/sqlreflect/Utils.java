package usf.java.sqlreflect;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.mapper.converter.Converter;
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
	
	public static <T extends Converter<?>> Class<?> converterReturnType(Class<T> clazz) throws NoSuchMethodException, SecurityException {
		return clazz.getDeclaredMethod("convert", Object.class).getReturnType();
	}
	
	public static boolean sameClass(Object o, Class<?> c) {
		return c.getName().equals(o.getClass().getName());
	}
	
	public static final String setterOf(String str){
		return "set" + Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
	public static final String getterOf(String str){
		return "get" + Character.toUpperCase(str.charAt(0)) + str.substring(1);
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
