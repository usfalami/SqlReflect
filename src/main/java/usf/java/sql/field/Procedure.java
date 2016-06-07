package usf.java.sql.field;

/**
 * 
 * @author YAH
 *
 */
public class Procedure extends ComplexSQL {
	
	public Procedure(String call) {
		super(call);
	}
	
	public Procedure(String schema, String name, String type){
		super(schema, name, type);
	}
}
