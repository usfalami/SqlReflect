package usf.java.sql.reflect.executor;

import java.io.Serializable;
import java.sql.SQLException;

import usf.java.sql.field.SQL;
import usf.java.sql.reflect.Reflector;
import usf.java.sql.reflect.executor.adapter.ExecutorAdapter;

public interface Executor extends Reflector<ExecutorAdapter> {
	
	public abstract void run(ExecutorAdapter adapter, SQL query, Serializable ... parameters) throws SQLException ;

}
