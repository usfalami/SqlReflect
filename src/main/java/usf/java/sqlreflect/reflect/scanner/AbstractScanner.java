package usf.java.sqlreflect.reflect.scanner;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionTimer;

public abstract class AbstractScanner<R> extends AbstractReflector<ConnectionManager, R> implements Scanner {
	
	private Mapper<R> mapper;
	
	public AbstractScanner(ConnectionManager cm, Mapper<R> mapper) {
		super(cm);
		this.mapper = mapper;
	}
	public AbstractScanner(ConnectionManager cm, ActionTimer at, Mapper<R> mapper) {
		super(cm, at);
		this.mapper = mapper;
	}
	
	public Mapper<R> getMapper() {
		return mapper;
	}
	
}
