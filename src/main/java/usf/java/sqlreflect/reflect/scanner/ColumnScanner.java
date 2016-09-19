package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.item.Column;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public class ColumnScanner extends AbstractFieldScanner<Column> {
	
	private String databasePattern, proecedurePattern, columnPattern;
	private SourceTypes source;
	
	
	public ColumnScanner(ConnectionManager cm) {
		this(cm, SourceTypes.TABLE);
	}
	
	public ColumnScanner(ConnectionManager cm, SourceTypes source) {
		super(cm);
		this.source = source;
	}
	
	public ColumnScanner set(String databasePattern, String proecedurePattern, String columnPattern) {
		this.databasePattern = databasePattern;
		this.proecedurePattern = proecedurePattern;
		this.columnPattern = columnPattern;
		return this;
	}

	@Override
	protected void run(DatabaseMetaData dm, Adapter<Column> adapter, TimePerform tp) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = tp.startAction(Constants.ACTION_EXECUTION);
			rs = source.getColumns(dm, databasePattern, proecedurePattern, columnPattern);
			action.end();
			
			Mapper<Column> mapper = source.getMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = tp.startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				Column column = mapper.map(rs, row+1);
				adapter.adapte(column, row++);
			}
			action.end();
			tp.setRowCount(row);
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}

}
