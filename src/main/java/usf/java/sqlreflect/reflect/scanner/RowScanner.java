package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.SqlUtils;

public class RowScanner<P, T> extends AbstractDataScanner<P, T> {
	
	private Mapper<T> mapper;

	public RowScanner(ConnectionManager cm, Mapper<T> mapper) {
		super(cm);
		this.mapper = mapper;
	}

	@Override
	protected void run(ResultSet rs, Adapter<T> adapter, TimePerform tp) throws Exception {
		if(mapper.getColumnNames() == null) // set all column if no column was set
			mapper.setColumnNames(SqlUtils.columnNames(rs));
		adapter.prepare(mapper);
		int row = 0;

		ActionPerform action = tp.startAction(Constants.ACTION_ADAPT);
		while(rs.next()) {
			T bean = mapper.map(rs, row+1);
			adapter.adapte(bean, row++);
		}
		action.end();
		tp.setRowCount(row);
	}

}
