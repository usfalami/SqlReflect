package usf.java.sqlreflect.mapper;

import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.sql.entry.Header;

public class ObjectTemplate<T> extends ComplexObject<T> implements Template<T> {

	public ObjectTemplate(Class<T> type) {
		super(null, type);
	}
	
	public ObjectTemplate(Class<T> type, List<Field<?>> fields) {
		super(null, type, fields);
	}

	@Override
	public void prepare(ResultSetMetaData rm) throws Exception {
		int count = rm.getColumnCount();
		Map<String, Header> map = new HashMap<String, Header>();
		for(int i=1; i<=count; i++){
			Header header = new Header();
			header.setColumnName(rm.getColumnName(i));
			header.setColumnClassName(rm.getColumnClassName(i));
			map.put(header.getColumnName(), header);
		}
		super.prepare(null, map);
	}
}
