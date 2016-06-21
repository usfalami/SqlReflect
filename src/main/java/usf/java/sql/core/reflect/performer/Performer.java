package usf.java.sql.core.reflect.performer;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Callable;

public interface Performer {
	
	public static interface HasExecutorPerformer {

		void preConnecion();
		void postConnecion();
		
		void preStatement();
		void postStatement();
		
		void preExec(Callable SQL) throws SQLException ;
		void postExec(Callable SQL, ResultSet rs) throws SQLException;
	}
	
}
