package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;
import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.ReflectorUtils;
import usf.java.sqlreflect.reflect.TimePerform;

public class RowScanner<T> extends AbstractDataScanner<T> {
	
	private Mapper<T> mapper;

	public RowScanner(ConnectionManager cm, Mapper<T> mapper) {
		super(cm);
		this.mapper = mapper;
	}

	@Override
	protected void run(Statement stmt, Adapter<T> adapter, TimePerform tp) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = tp.startAction(Constants.ACTION_EXECUTION);
			rs = getConnectionManager().executeQuery(stmt, getCallable().getSQL(), getParameters());
			action.end();
			
			if(mapper.getColumnNames() == null) // set all column if no column was set
				mapper.setColumnNames(ReflectorUtils.columnNames(rs));
			adapter.prepare(mapper);
			int row = 0;

			action = tp.startAction(Constants.ACTION_ADAPT);
			while(rs.next()) {
				T bean = mapper.map(rs, row+1);
				adapter.adapte(bean, row++);
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
