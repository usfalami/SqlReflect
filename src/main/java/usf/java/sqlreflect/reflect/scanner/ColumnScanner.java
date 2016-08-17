package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.TimePerform;

public class ColumnScanner extends AbstractFieldScanner<Column> {
	
	private String databasePattern, proecedurePattern, columnPattern;
	private HasColumn field;
	
	public ColumnScanner(ConnectionManager cm, HasColumn field) {
		super(cm);
		this.field = field;
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
			
			tp.execStart();
			rs = field.getColumns(dm, databasePattern, proecedurePattern, columnPattern);
			tp.execEnd();
			
			Mapper<Column> mapper = field.getMapper();
			adapter.prepare(mapper);
			int row = 0;

			tp.adaptStart();
			while(rs.next()){
				Column column = mapper.map(rs, row+1);
				adapter.adapte(column, row++);
			}
			tp.adaptEnd();
			tp.setRowCount(row);
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}

}
