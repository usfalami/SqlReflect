package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.exception.AdapterException;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ReflectorUtils;

public class RowScanner<T> extends AbstractDataScanner<T> {
	
	private Mapper<T> mapper;

	public RowScanner(ConnectionManager cm, Mapper<T> mapper) {
		super(cm);
		this.mapper = mapper;
	}

	@Override
	protected void run(Statement stmt, ScannerAdapter<T> adapter) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = getConnectionManager().executeQuery(stmt, getCallable().getSQL());
			if(mapper.getColumnNames() == null) // set all column if no column was set
				mapper.setColumnNames(ReflectorUtils.columnNames(rs));
			adapter.prepare(mapper);
			int row = 0;
			while(rs.next()) {
				T bean = mapper.map(rs, row+1);
				adapter.adapte(bean, row++);
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
			adapter.end();
		}
	}

}
