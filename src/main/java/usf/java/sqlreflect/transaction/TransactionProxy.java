package usf.java.sqlreflect.transaction;

import java.lang.reflect.Method;
import java.sql.SQLException;

import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.Transaction;

public class TransactionProxy implements Transaction {

	private Class<? extends MultiTransaction> clazz;
	private String methodName;
	
	public TransactionProxy(Class<? extends MultiTransaction> clazz, String methodName) {
		this.clazz = clazz;
		this.methodName = methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getMethodName() {
		return methodName;
	}

	@Override
	public void execute(ReflectorFactory rf) throws SQLException {
		if(clazz == null || Utils.isEmptyString(methodName)) throw new SQLException(""); //TODO throw Exception
		try{
			Object obj = clazz.newInstance();
			Method method = obj.getClass().getDeclaredMethod(methodName, ReflectorFactory.class);
			method.invoke(obj, rf);
		}catch(Exception e){
			throw new SQLException("No match method was found for " + methodName + "(" + rf.getClass().getName() + ")"); //TODO check this
		}
	}
}
