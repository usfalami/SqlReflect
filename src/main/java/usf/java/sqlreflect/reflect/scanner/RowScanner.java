package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;

public class RowScanner<A, R> extends AbstractDataScanner<A, R> {
	
	private Mapper<R> mapper;

	public RowScanner(ConnectionManager cm, Mapper<R> mapper) {
		super(cm);
		this.mapper = mapper;
	}
	public RowScanner(ConnectionManager cm, ActionTimer at, Mapper<R> mapper) {
		super(cm, at);
		this.mapper = mapper;
	}

	@Override
	protected void runScan(ResultSet rs, Adapter<R> adapter, ActionTimer at) throws Exception {
		if(mapper.getColumnNames() == null) // set all column if no column was set
			mapper.setColumnNames(Utils.columnNames(rs));
		adapter.prepare(mapper);
		int row = 0;

		while(rs.next()) {
			R bean = mapper.map(rs, row+1);
			adapter.adapte(bean, row++);
		}
	}

}
