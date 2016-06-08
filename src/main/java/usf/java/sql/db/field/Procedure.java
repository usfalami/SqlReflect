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
	
	public Procedure(String database, String name, String type){
		super(database, name, type);
	}
}
