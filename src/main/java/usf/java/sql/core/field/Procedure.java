package usf.java.sql.core.field;

public class Procedure extends Function {

	public Procedure(String database, String name) {
		super(database, name);
	}

	public Procedure(String sql) {
		super(sql);
	}

}
