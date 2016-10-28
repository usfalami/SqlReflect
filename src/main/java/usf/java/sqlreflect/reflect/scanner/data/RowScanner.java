package usf.java.sqlreflect.reflect.scanner.data;

import java.sql.ResultSet;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;

public class RowScanner<A, R> extends AbstractDataScanner<A, R> {
	
	public RowScanner(ConnectionManager cm, Mapper<R> mapper) {
		super(cm, mapper);
	}
	public RowScanner(ConnectionManager cm, ActionTimer at, Mapper<R> mapper) {
		super(cm, at, mapper);
	}

	@Override
	protected void runAdapt(ResultSet rs, Adapter<R> adapter, ActionTimer at) throws Exception {
		int row = 0;
		while(rs.next()) {
			R bean = getMapper().map(rs, row+1);
			adapter.adapte(bean, row++);
		}
	}
}
