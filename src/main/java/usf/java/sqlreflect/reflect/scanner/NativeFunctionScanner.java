package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.type.NativeFunctions;

public class NativeFunctionScanner extends AbstractFieldScanner<String> {
	
	private NativeFunctions nf;
	
	public NativeFunctionScanner(ConnectionManager cm) {
		super(cm);
	}
	public NativeFunctionScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<String> adapter) throws Exception {
		ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
		String[] functions = nf.getFunctions(dm);
		action.end();
		
		adapter.prepare(null);

		action = getTimePerform().startAction(Constants.ACTION_ADAPT);
		for(int i=0; i<functions.length; i++)
			adapter.adapte(functions[i], i);
		action.end();
		
		getTimePerform().setRowCount(functions.length);
	}
	
	public NativeFunctionScanner set(NativeFunctions nf) throws Exception {
		this.nf = nf;
		return this;
	}
	
}
