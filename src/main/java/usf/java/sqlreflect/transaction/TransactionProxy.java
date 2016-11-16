package usf.java.sqlreflect.transaction;

import java.lang.reflect.Method;
import java.sql.SQLException;

import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.Transaction;

public class TransactionProxy implements Transaction {

	private MultiTransaction mt;
	private String methodName;
	
	private TransactionProxy(MultiTransaction mt, String methodName) {
		this.mt = mt;
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
		try{
			Method method = mt.getClass().getDeclaredMethod(methodName, ReflectorFactory.class);//throws exception if method not exist
			method.invoke(mt, rf);
		}catch(Exception e){
			throw new SQLException("No match method was found for " + methodName + "(" + rf.getClass().getName() + ")"); //TODO check this
		}
	}
	
	public static TransactionProxy get(String className, String methodName) throws Exception {
		if(Utils.isEmptyString(className) || Utils.isEmptyString(methodName)) throw new Exception(""); //TODO check this exception
		Class<?> clazz = Class.forName(className);
		if(clazz.isAssignableFrom(MultiTransaction.class)) throw new ClassCastException(); //TODO check this exception
		Class<MultiTransaction> multiTransactionClazz = (Class<MultiTransaction>) clazz;
		return new TransactionProxy(multiTransactionClazz.newInstance(), methodName);
	}
	public static <T extends MultiTransaction> TransactionProxy get(Class<T> clazz, String methodName) throws Exception {
		if(clazz == null || Utils.isEmptyString(methodName)) throw new Exception(""); //TODO check this exception
		return new TransactionProxy(clazz.newInstance(), methodName);
	}
	public static <T extends MultiTransaction> TransactionProxy get(MultiTransaction mt, String methodName) throws Exception {
		if(mt == null || Utils.isEmptyString(methodName)) throw new Exception(""); //TODO check this exception
		return new TransactionProxy(mt, methodName);
	}
	
}
