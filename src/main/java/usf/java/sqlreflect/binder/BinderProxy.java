package usf.java.sqlreflect.binder;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.reflect.Utils;

public class BinderProxy<T> implements Binder<T> {

	private MultipleBinder<T> mb;
	private String binderMethodName, postBinderMethodName;
	private Method binderMethod, postBinderMethod;
	
	private BinderProxy(MultipleBinder<T> mb, String binderMethod, String postBinderMethod) {
		this.mb = mb;
		this.binderMethodName = binderMethod;
		this.postBinderMethodName = postBinderMethod;
	}
	
	@Override
	public final void bind(Statement stmt, T item) throws SQLException {
		if(binderMethod == null)
			binderMethod = search(binderMethodName, stmt, item);
		invok(binderMethod, stmt, item);
	}
	@Override
	public final void post(Statement stmt, T item) throws SQLException {
		if(Utils.isEmptyString(postBinderMethodName)) return; //optional
		if(postBinderMethod == null)
			postBinderMethod = search(postBinderMethodName, stmt, item);
		invok(postBinderMethod, stmt, item);
	}

	private Method search(String methodName, Statement stmt, T item) throws SQLException {
		try{
			return Utils.findMethod(mb, methodName, stmt, item);
		}catch(Throwable e){
			throw new SQLException("No match method was found for " +
					methodName + "(" + stmt.getClass().getName() + "," + item.getClass().getName()+")"); //TODO check this
		}
	}
	private void invok(Method method, Statement stmt, T item) throws SQLException {
		try {
			method.invoke(mb, stmt, item);
		} catch (Throwable e) {
			throw new SQLException("An error occurred while executing " + method.getName(), e);
		}
	}
	
	public static <T> BinderProxy<T> get(String className, String binderMethod, String postBinderMethod) throws Exception {
		if(Utils.isEmptyString(className) || Utils.isEmptyString(binderMethod)) throw new Exception("");
		Class<?> clazz = Class.forName(className);
		if(clazz.isAssignableFrom(MultipleBinder.class)) throw new ClassCastException(); //TODO check this exception
		Class<MultipleBinder<T>> multiBinderClazz = (Class<MultipleBinder<T>>) clazz; 
		return new BinderProxy<T>(multiBinderClazz.newInstance(), binderMethod, postBinderMethod);
	}
	public static <T, B extends MultipleBinder<T>> BinderProxy<T> get(Class<B> clazz, String binderMethod, String postBinderMethod) throws Exception {
		if(clazz == null || Utils.isEmptyString(binderMethod)) throw new Exception(""); //TODO check this exception
		MultipleBinder<T> obj = clazz.newInstance();
		return new BinderProxy<T>(obj, binderMethod, postBinderMethod);
	}
	public static <T> BinderProxy<T> get(MultipleBinder<T> mb, String binderMethod, String postBinderMethod) throws Exception {
		if(mb == null || Utils.isEmptyString(binderMethod)) throw new Exception(""); //TODO check this exception
		return new BinderProxy<T>(mb, binderMethod, postBinderMethod);
	}

	public static <T> BinderProxy<T> get(String className, String binderMethod) throws Exception {
		return get(className, binderMethod, null);
	}
	public static <T, B extends MultipleBinder<T>> BinderProxy<T> get(Class<B> clazz, String binderMethod) throws Exception {
		return get(clazz, binderMethod, null);
	}
	public static <T> BinderProxy<T> get(MultipleBinder<T> mb, String binderMethod) throws Exception {
		return get(mb, binderMethod, null);
	}

}
