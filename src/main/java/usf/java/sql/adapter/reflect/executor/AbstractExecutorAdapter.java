package usf.java.sql.adapter.reflect.executor;

import java.io.Serializable;
import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.AbstractReflectorAdapter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.SQL;
import usf.java.sql.core.reflect.executor.Executor;
import usf.java.sql.core.reflect.executor.StatementExecutor;

public abstract class AbstractExecutorAdapter extends AbstractReflectorAdapter implements ExecutorAdapter {
	
	public AbstractExecutorAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	@Override
	public void execute(String query, Serializable... parameters) throws SQLException { //one preparedStatement
		Executor e = Utils.executorFor(parameters);
		SQL sql = cm.parseSQL(query);
		if(sql != null)
			e.run(this, sql, parameters);
	}
	
	public void execute(String... queries) throws SQLException { // only statments
		if(queries == null) return;
		Executor e = new StatementExecutor();
		for(String query : queries)
			e.run(this, cm.parseSQL(query));
	}
	public void execute(String[] queries, Serializable[][] parameters) throws SQLException {
		if(queries == null || parameters==null || queries.length != parameters.length) return; //throw exception
		for(int i=0; i<queries.length; i++)
			this.execute(queries[i], parameters[i]);
	}

}
