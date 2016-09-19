package usf.java.sqlreflect.sql;

import java.util.ArrayList;
import java.util.Arrays;

public class Parameters extends ArrayList<Parameter<?>> {

	private static final long serialVersionUID = 4503476961701784337L;
	
	public Parameters(Parameter<?>... parameters) {
		super(Arrays.asList(parameters));
	}
	
	public Parameter<?>[] toArray(){
		return toArray(new Parameter<?>[size()]);
	}

}
