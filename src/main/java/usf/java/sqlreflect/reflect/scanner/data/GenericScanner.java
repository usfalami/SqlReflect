package usf.java.sqlreflect.reflect.scanner.data;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.generic.GenericTypeMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.GenericType;

public class GenericScanner<A> extends AbstractDataScanner<A, GenericType> {

	public GenericScanner(ConnectionManager cm) {
		super(cm, new GenericTypeMapper<GenericType>(GenericType.class));
	}
	
	public GenericScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new GenericTypeMapper<GenericType>(GenericType.class));
	}

}
