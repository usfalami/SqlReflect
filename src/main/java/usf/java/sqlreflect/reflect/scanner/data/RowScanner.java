package usf.java.sqlreflect.reflect.scanner.data;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.generic.GenericTypeMapper;
import usf.java.sqlreflect.reflect.ActionTimer;

public class RowScanner<A, R> extends AbstractDataScanner<A, R> {
	
	public RowScanner(ConnectionManager cm, Mapper<R> mapper) {
		super(cm, mapper);
	}
	public RowScanner(ConnectionManager cm, ActionTimer at, Mapper<R> mapper) {
		super(cm, at, mapper);
	}

}
