package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.scanner.AbstractScannerAdapter.CallableComparator;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.mapper.ProcedureMapper;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;

public class ProcedureColumnsComparator extends AbstractScannerAdapter implements CallableComparator<Procedure> {
	
	protected String callableName;
	protected List<Function> functions;
	protected List<Column[]> columnsList;

	public ProcedureColumnsComparator(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
		functions = new ArrayList<Function>();
		columnsList = new ArrayList<Column[]>();
	}

	@Override
	public void start() { }

	@Override
	public void adapte(Procedure procedure) {
		if(callableName.equals(procedure.getName())){
			Column[] columns = procedure.getColumns();
			functions.add(procedure);
			columnsList.add(columns);
		}
	}
	
	@Override
	public void finish() {
		Column[] c1, c2;
		Function f1, f2;
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
	
	@Override
	public void compare(String callableName) throws SQLException {
		this.callableName = callableName;
		if(callableName == null || callableName.isEmpty()) return;
		new ProcedureScanner().run(this, new ProcedureMapper(), null, callableName);
	}

}
