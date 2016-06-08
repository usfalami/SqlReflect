package usf.java.sql.reflect.parser.adapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Function;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.parser.adapter.AbstractParserAdapter.FunctionParserAdapter;

public class FunctionColumnComparatorAdapter extends FunctionParserAdapter {
	
	protected String pattern;
	protected List<Function> functions;
	protected List<Column[]> columnsList;

	public FunctionColumnComparatorAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
		functions = new ArrayList<Function>();
		columnsList = new ArrayList<Column[]>();
	}

	@Override
	public void start() { }

	@Override
	public void performFunction(Function procedure, Column... columns) {
		if(pattern.equals(procedure.getName())){
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
	public void listProcedure(String database, String pattern) throws SQLException {
		this.pattern = pattern;
		if(pattern == null || pattern.isEmpty()) return;
		super.listProcedure(database, pattern);
	}

}
