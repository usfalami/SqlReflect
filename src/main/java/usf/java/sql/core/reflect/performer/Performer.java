package usf.java.sql.core.reflect.performer;

import java.sql.SQLException;

import usf.java.sql.core.reflect.Reflector.Adapter;

public interface Performer {
	
	public static interface PerformAdapter extends Adapter {

		void preConnecion();
		void postConnecion();
		
		void preStatement();
		void postStatement();
		
		void preExec() throws SQLException ;
		void postExec(int rowCount) throws SQLException;
	}
	
}
