package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.type.NativeFunctions;

public class NativeFunctionScanner extends AbstractFieldScanner<String> {
	
	private NativeFunctions nf;
	
	public NativeFunctionScanner(ConnectionManager cm) {
		super(cm, null);
	}
	public NativeFunctionScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, null);
	}

	@Override
	public void run(Adapter<String> adapter, ActionTimer at) throws Exception {
		DatabaseMetaData dm = getConnectionManager().getConnection().getMetaData();
		
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
	
	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return null;
	}
	
}
