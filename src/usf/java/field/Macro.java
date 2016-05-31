package usf.java.field;

public class Macro extends ComplexSQL {

	public Macro(String exec) {
		super(exec);
	}

	public Macro(String schema, String name, Parameter[] parameter) {
		super(schema, name, parameter);
	}
	
}
