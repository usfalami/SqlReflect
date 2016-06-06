package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.SQLException;

import usf.java.field.SQL;
import usf.java.reflect.Reflector;
import usf.java.reflect.executor.adapter.ExecutorAdapter;

public interface Executor extends Reflector<ExecutorAdapter> {
	
	public abstract void run(ExecutorAdapter adapter, SQL query, Serializable ... parameters) throws SQLException ;

}
