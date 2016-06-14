package usf.java.sql.core.reflect.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.SQL;
import usf.java.sql.core.reflect.Reflector;

public interface Executor extends Reflector {
	
	public abstract void run(HasExecutor adapter, SQL query, Serializable ... parameters) throws SQLException;
	
	public static interface HasExecutor extends Reflector.HasReflector {

		void preConnecion();
		void postConnecion();
		
		void preStatement();
		void postStatement();
		
		void preExec(SQL SQL) throws SQLException ;
		void postExec(SQL SQL, ResultSet rs) throws SQLException;
	}

}
