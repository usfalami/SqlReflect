package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.TableMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.item.Table;
import usf.java.sqlreflect.sql.type.TableTypes;

public class TableScanner extends AbstractFieldScanner<Table> {
	
	private String databasePattern, tablePattern;
	private boolean columns;
	private String[] types;
	
	public TableScanner(ConnectionManager cm) {
		super(cm, new TableMapper());
	}
	public TableScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new TableMapper());
	}

	@Override
	protected ResultSet runScan(DatabaseMetaData dm) throws Exception {
		return dm.getTables(null, databasePattern, tablePattern, types);
	}
	
	public TableScanner set(String databasePattern, String tablePattern, boolean columns, TableTypes... types) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		this.columns = columns;
		if(Utils.isEmpty(types)) 
			this.types = new String[]{TableTypes.TABLE.toString()};
		else{
			this.types = new String[types.length];
			for(int i=0; i<types.length; i++)
				this.types[i] = types[i].toString();
		}
		return this;
	}
	public TableScanner set(String databasePattern, String tablePattern, boolean columns) {
		return set(databasePattern, tablePattern, columns, TableTypes.TABLE);
	}
	public TableScanner set(String databasePattern, String tablePattern) {
		return set(databasePattern, tablePattern, false, TableTypes.TABLE);
	}
}
