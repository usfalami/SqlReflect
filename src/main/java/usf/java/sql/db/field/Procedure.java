package usf.java.sql.db.field;

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
