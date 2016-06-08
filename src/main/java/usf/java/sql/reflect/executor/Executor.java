package usf.java.sql.reflect.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.db.field.SQL;
import usf.java.sql.reflect.Reflector;

public interface Executor extends Reflector<Executor.Adapter> {
	
	public abstract void run(Executor.Adapter adapter, SQL query, Serializable ... parameters) throws SQLException;
	
	public static interface Adapter extends Reflector.Adapter {

		void preConnecion();
		void postConnecion();
		
		void preStatement();
		void postStatement();
		
		void preExec(SQL SQL) throws SQLException ;
		void postExec(SQL SQL, ResultSet rs) throws SQLException;
	}

}
