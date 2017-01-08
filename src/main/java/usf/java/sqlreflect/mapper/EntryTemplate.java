package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Header;

public class EntryTemplate<T extends Entry> extends ComplexProperty<T> implements Template<T> {
	
	public EntryTemplate(Class<T> type, String... propertiesNames) {
		super(null, type);
		if(!Utils.isEmptyArray(propertiesNames))
			for(String propertyName : propertiesNames)
				fields.add(new EntryProperty<T>(propertyName));
	}

	@Override
	public void prepare(ResultSetMetaData metaData) throws Exception {
		Map<String, Header> headers = new HashMap<String, Header>();
		if(Utils.isEmptyCollection(fields))
			prepareAndFillProperties(metaData, headers);
		else
			prepare(metaData, headers);
		super.prepare(null, headers);
	}
	
	@Override
	public T get(ResultSet rs) throws Exception {
		T obj = type.newInstance();
		for(Field<?> field : fields){
			Object value = field.get(rs);
			obj.set(field.getName(), value);
		}
		return obj;
	}
	
	private void prepare(ResultSetMetaData metaData, Map<String, Header> headers) throws Exception {
		int count = metaData.getColumnCount();
		for(int i=1; i<=count; i++){
			Header header = buildHeader(metaData, i);
			headers.put(header.getColumnName(), header);
		}
	}
	private void prepareAndFillProperties(ResultSetMetaData metaData, Map<String, Header> headers) throws Exception {
		int count = metaData.getColumnCount();
		for(int i=1; i<=count; i++){
			Header header = buildHeader(metaData, i);
			headers.put(header.getColumnName(), header);
			Field<Object> field = new EntryProperty<Object>(header.getColumnName());
			field.setType((Class<Object>) Class.forName(header.getColumnClassName()));
			fields.add(field);
		}
	}
	private Header buildHeader(ResultSetMetaData metaData, int index) throws SQLException{
		Header header = new Header();
		header.setColumnName(metaData.getColumnName(index));
		header.setColumnClassName(metaData.getColumnClassName(index));
		return header;
	}
	
}
