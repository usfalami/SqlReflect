package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.type.NativeFunctions;

public class NativeFunctionScanner extends AbstractFieldScanner<String> {
	
	private NativeFunctions nf;
	
	public NativeFunctionScanner(ConnectionManager cm) {
		super(cm);
	}
	public NativeFunctionScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at);
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<String> adapter, ActionTimer at) throws Exception {
		ActionTimer action = at.startAction(Constants.ACTION_EXECUTION);
		String[] functions = nf.getFunctions(dm);
		action.end();
		
		adapter.prepare(null);

		action = at.startAction(Constants.ACTION_ADAPT);
		for(int i=0; i<functions.length; i++)
			adapter.adapte(functions[i], i);
		action.end();
		
	}
	
	public NativeFunctionScanner set(NativeFunctions nf) throws Exception {
		this.nf = nf;
		return this;
	}
	
}
