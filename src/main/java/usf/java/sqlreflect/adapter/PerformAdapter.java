package usf.java.sqlreflect.adapter;


public interface PerformAdapter extends Adapter {

	void preConnecion();
	void postConnecion();
	
	void preStatement();
	void postStatement();
	
	void preExec() throws Exception ;
	void postExec(int rowCount) throws Exception;
}