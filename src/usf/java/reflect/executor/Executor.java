package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.SQLException;

import usf.java.reflect.Reflector;

public interface Executor extends Reflector<ExecutorAdapter> {
	
	public abstract void run(ExecutorAdapter adapter, String query, Serializable ... parameters) throws SQLException ;

}
