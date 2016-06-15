package usf.java.sql.adapter.reflect.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.java.sql.adapter.reflect.AbstractReflectorAdapter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.mapper.BeanMapper;
import usf.java.sql.core.reflect.executor.Executor;

public class ExecutorMapper<T> extends AbstractReflectorAdapter implements ExecutorAdapter {
	
	protected List<T> beans;
	protected BeanMapper<T> mapper;
	
	public ExecutorMapper(ConnectionManager cm, BeanMapper<T> mapper) {
		super(cm);
		this.mapper = mapper;
	}

	@Override
	public void preExec(Callable SQL) { 
		beans = new ArrayList<T>();
	}

	@Override
	public void execute(String query, Serializable... parameters) throws SQLException { //one preparedStatement
		Executor e = Utils.executorFor(parameters);
		Callable sql = cm.parseSQL(query);
		if(sql != null)
			e.run(this, sql, parameters);
	}

	@Override
	public void postExec(Callable SQL, ResultSet rs) throws SQLException {
		int row = 1;
		while(rs.next()) beans.add(mapper.map(rs, row++));
	}
	
	public List<T> getBeans() {
		return beans;
	}
	
	@Override
	public void preConnecion() { }

	@Override
	public void postConnecion() { }

	@Override
	public void preStatement() { }

	@Override
	public void postStatement() { }
	

}
