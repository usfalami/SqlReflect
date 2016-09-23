package usf.java.sqlreflect.sql.type;

public enum ParameterTypes {

	UNKNOWN, IN, INOUT, OUT, RETURN, RESULTSET;
	
	public static boolean isIN(ParameterTypes type){
		return IN.equals(type) || INOUT.equals(type);
	}
	
	public static boolean isOut(ParameterTypes type){
		return OUT.equals(type) || INOUT.equals(type);
	}
}
