package usf.java.sqlreflect.adapter;

import java.sql.SQLException;

public interface PerformAdapter extends Adapter {

	void preConnecion();
	void postConnecion();
	
	void preStatement();
	void postStatement();
	
	void preExec() throws SQLException ;
	void postExec(int rowCount) throws SQLException;
}