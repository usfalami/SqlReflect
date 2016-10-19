package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.HeaderMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.item.Header;

public class HeaderScanner<A> extends AbstractDataScanner<A, Header> {

	public HeaderScanner(ConnectionManager cm) {
		super(cm);
	}
	public HeaderScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at);
	}

	@Override
	protected void runScan(ResultSet rs, Adapter<Header> adapter, ActionTimer at) throws Exception {
		Mapper<Header> mapper = new HeaderMapper();
		ResultSetMetaData rm = rs.getMetaData();
		adapter.prepare(mapper);

		for(int i=0; i<rm.getColumnCount(); i++) {
			Header col = mapper.map(rs, i+1);
			adapter.adapte(col, i++);
		}
	}
	
}
