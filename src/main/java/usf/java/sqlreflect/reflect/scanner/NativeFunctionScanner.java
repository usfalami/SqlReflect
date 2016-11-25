package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.type.NativeFunctions;

public class NativeFunctionScanner extends AbstractScanner<String> {
	
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
		action.end();//ACTION_EXECUTION end

		action = at.startAction(Constants.ACTION_PROCESSING);
		adapter.prepare(null);
		for(int i=0; i<functions.length; i++)
			adapter.adapte(functions[i], i);
		action.end();
	}
	
	@Override
	protected void validateArgs() {
		super.validateArgs();
		if(Utils.isNull(nf)) throw new IllegalArgumentException("NativeFunction type can't be null");
	}
	
	public NativeFunctionScanner set(NativeFunctions nf) throws Exception {
		this.nf = nf;
		return this;
	}
	
}
