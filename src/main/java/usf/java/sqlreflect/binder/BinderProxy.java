package usf.java.sqlreflect.binder;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.reflect.Utils;

public class BinderProxy<T> implements Binder<T> {

	private Class<? extends MultipleBinder<T>> clazz;
	private String binderMethod, postBinderMethod;
	
	public BinderProxy(Class<MultipleBinder<T>> clazz) {
		this(clazz, null, null);
	}
	public BinderProxy(Class<? extends MultipleBinder<T>> clazz,  String binderMethod) {
		this(clazz, binderMethod, null);
	}
	public BinderProxy(Class<? extends MultipleBinder<T>> clazz,  String binderMethod, String postBinderMethod) {
		this.clazz = clazz;
		this.binderMethod  = binderMethod;
		this.postBinderMethod  = postBinderMethod;
	}
	
	@Override
	public final void bind(Statement stmt, T item) throws SQLException {
		invok(binderMethod, stmt, item);
	}
	@Override
	public final void post(Statement stmt, T item) throws SQLException {
		invok(postBinderMethod, stmt, item);
	}
	
	public String getBinderMethod() {
		return binderMethod;
	}
	public void setBinderMethod(String binderMetod) {
		this.binderMethod = binderMetod;
	}
	public String getPostBinderMethod() {
		return postBinderMethod;
	}
	public void setPostBinderMethod(String binderMethod) {
		this.postBinderMethod = binderMethod;
	}

	private void invok(String methodName, Statement stmt, T item) throws SQLException {
		if(clazz == null || Utils.isEmptyString(methodName)) return;
		try{
			Object obj = clazz.newInstance();
			Method[] list = obj.getClass().getDeclaredMethods();
			int i=0;
			while(i<list.length){
				if(list[i].getName().equals(methodName)){
					Class<?>[] clazz = list[i].getParameterTypes();
					if(clazz.length == 2 && clazz[0].isInstance(stmt) && clazz[1].isInstance(item))
						break;
				}i++;
			}
			if(i<list.length)
				list[i].invoke(obj, stmt, item);
			else 
				throw new SQLException(); //TODO check this
		}catch(Exception e){
			throw new SQLException("No match method was found for " + methodName + "(" + stmt.getClass().getName() + "," + item.getClass().getName()+")"); //TODO check this
		}
	}

}
