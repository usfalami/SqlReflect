package usf.java.field;

/**
 * 
 * @author YAH
 *
 */
public class Procedure extends ComplexSQL {
	
	public Procedure(String call) {
		super(call);
	}
	
	public Procedure(String schema, String name, Parameter[] parameter){
		super(schema, name, parameter);
	}
}
