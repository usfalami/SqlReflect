package usf.java.sql.core.reflect.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Callable;

public interface Executor  {
	
	public abstract void run(HasExecutor adapter, Callable query, Serializable ... parameters) throws SQLException;
	
	public static interface HasExecutor {

		void preConnecion();
		void postConnecion();
		
		void preStatement();
		void postStatement();
		
		void preExec(Callable SQL) throws SQLException ;
		void postExec(Callable SQL, ResultSet rs) throws SQLException;
	}

}
