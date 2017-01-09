package usf.java.sqlreflect.reflect.scanner.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.generic.HeaderMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.Header;

public class HeaderScanner<A> extends AbstractDataScanner<A, Header> {

	public HeaderScanner(ConnectionManager cm) {
		super(cm, new HeaderMapper());
	}
	public HeaderScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new HeaderMapper());
	}

	@Override
	protected void runProcessing(ResultSet rs, Adapter<Header> adapter, ActionTimer at) throws Exception {
		ResultSetMetaData rm = rs.getMetaData();
		int count = rm.getColumnCount();
		for(int i=0; i<count; i++) {
			Header col = getMapper().map(rs, i+1);
			adapter.adapte(col, i);
		}
	}
	
}
