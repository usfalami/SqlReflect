package usf.java.sqlreflect.binder;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Statement;

public class MultipleBinder<T> implements Binder<T> {

	private String binderMethod;
	private String postBinderMethod;
	
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

	private void invok(String methodName, Statement stmt, T item) throws SQLException{
		try{
			int i=0;
			Method[] list = this.getClass().getDeclaredMethods();
			while(i<list.length && list[i].getName().equals(methodName)) i++;
			if(i<list.length){
				Class<?>[] clazz = list[i].getParameterTypes();
				if(clazz.length == 2 && clazz[i].equals(Statement.class) && clazz[i].equals(item.getClass()))
					list[i].invoke(this, stmt, item);
				else new SQLException(methodName + " parameters are not correct"); //TODO check this
			}
			else new SQLException("No match method was found for " + methodName); //TODO check this
		}catch(Exception e){
			throw new SQLException("No match method was found for " + methodName); //TODO check this
		}
	}

}
