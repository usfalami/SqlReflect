package usf.java.sql.core.reflect.performer;

import java.sql.SQLException;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.reflect.Worker;
import usf.java.sql.core.reflect.Reflector.Adapter;

public interface Performer extends Worker<Performer.PerformAdapter> {

	TimePerform run() throws SQLException, AdapterException;
	
	public static interface PerformAdapter extends Adapter {

		void preConnecion();
		void postConnecion();
		
		void preStatement();
		void postStatement();
		
		void preExec() throws SQLException ;
		void postExec(int rowCount) throws SQLException;
	}
	
}
