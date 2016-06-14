package usf.java.sql.core.db.field;

public class Procedure extends Callable {

	public Procedure(String database, String name) {
		super(database, name);
	}

	public Procedure(String sql) {
		super(sql);
	}

}
