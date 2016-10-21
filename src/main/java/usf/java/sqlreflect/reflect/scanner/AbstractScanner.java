package usf.java.sqlreflect.reflect.scanner;

import java.util.Collection;

import usf.java.sqlreflect.adapter.ListAdapter;
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
	
	public final Collection<R> run() throws Exception {
		ListAdapter<R> adapter = new ListAdapter<R>();
		run(adapter);
		return adapter.getList();
	}
	
	public Mapper<R> getMapper() {
		return mapper;
	}
	
}
