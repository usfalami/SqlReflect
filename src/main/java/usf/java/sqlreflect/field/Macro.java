package usf.java.sqlreflect.field;

public class Macro extends Callable {

	public Macro(String database, String name) {
		super(database, name);
	}

	public Macro(String sql) {
		super(sql);
	}

}