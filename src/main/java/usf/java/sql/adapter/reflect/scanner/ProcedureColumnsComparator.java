package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import usf.java.sql.core.adapter.ScannerAdapter;
import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;

public class ProcedureColumnsComparator implements ScannerAdapter<Procedure> {
	
	protected String callableName;
	protected List<Callable> functions;
	protected List<Column[]> columnsList;

	public ProcedureColumnsComparator() {
		functions = new ArrayList<Callable>();
		columnsList = new ArrayList<Column[]>();
	}

	@Override
	public void start() { }

	@Override
	public void headers(String... headers) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void adapte(Procedure procedure, int index) {
		if(callableName.equals(procedure.getName())){
//			Column[] columns = procedure.getColumns();
//			functions.add(procedure);
//			columnsList.add(columns);
		}
	}
	
	@Override
	public void end() {
		Column[] c1, c2;
		Callable f1, f2;
		for(int i=0; i<columnsList.size()-1; i++){
			f1=functions.get(i);
			c1=columnsList.get(i);
			for(int j=i+1; j<columnsList.size(); j++){
				f2=functions.get(j);
				c2=columnsList.get(j);
				boolean eq = Arrays.equals(c1, c2);
				System.out.print(f1.getDatabase());
				System.out.print(" & ");
				System.out.print(f2.getDatabase());
				System.out.println("\t" + (eq ? "IDENTIC" : "DIFERRENT"));
			}
		}
	}
	

	
	public void compare(ConnectionManager cm, String callableName) throws SQLException, AdapterException {
		this.callableName = callableName;
		if(callableName == null || callableName.isEmpty()) return;
		ProcedureScanner s = new ProcedureScanner(cm);
		s.set(null, callableName, true);
		s.run(this);
	}

}
