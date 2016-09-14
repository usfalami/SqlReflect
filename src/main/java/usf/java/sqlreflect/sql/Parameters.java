package usf.java.sqlreflect.sql;

import java.util.ArrayList;

public class Parameters extends ArrayList<Parameter<?>> {

	private static final long serialVersionUID = 4503476961701784337L;
	
	public Parameters(Parameter<?>... parameters) {
		if(parameters == null) return;
		for(Parameter<?> param : parameters)
			add(param);
	}

}
