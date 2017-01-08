package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;
import java.util.Collection;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.FullWriter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.adapter.ListWriter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.stream.StreamWriter;

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
		
	protected void runPreparation(Adapter<R> adapter, ResultSet rs) throws Exception {
		DatabaseType type = getConnectionManager().getServer().getDatabaseType();
		Template<R> headers = mapper.prepare(rs, type);
		adapter.prepare(headers);
	}
	protected void runProcessing(ResultSet rs, Adapter<R> adapter, ActionTimer at) throws Exception {
		int row = 0;
		while(rs.next()){
			R field = getMapper().map(rs, row+1);
			adapter.adapte(field, row++);
		}
	}
	
	public Mapper<R> getMapper() {
		return mapper;
	}
	
	public void setMapper(Mapper<R> mapper) {
		this.mapper = mapper;
	}
	
	public final Collection<R> run() throws Exception {
		ListAdapter<R> adapter = new ListAdapter<R>();
		run(adapter);
		return adapter.getList();
	}
	public final void write(StreamWriter sw) throws Exception {
		run(new ListWriter<R>(sw));
	}
	public final void writeAll(StreamWriter sw) throws Exception {
		run(new FullWriter<R>(sw));
	}
	
}
