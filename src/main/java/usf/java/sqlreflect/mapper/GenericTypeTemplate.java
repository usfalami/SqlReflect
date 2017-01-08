package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.sql.entry.GenericType;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public class GenericTypeTemplate<T extends GenericType> extends ComplexProperty<T> implements Template<T> {
	
	public GenericTypeTemplate(Class<T> type, String... propertiesNames) {
		super(null, type);
		if(!Utils.isEmptyArray(propertiesNames))
			for(String propertyName : propertiesNames)
				fields.add(new SimpleProperty<T>(propertyName));
	}

	@Override
	public void prepare(ResultSetMetaData metaData) throws Exception {
		if(Utils.isNotNull(metaData)){
			Map<String, Header> headers = new HashMap<String, Header>();
			if(Utils.isEmptyCollection(fields))
				prepareAndFillProperties(metaData, headers);
			else
				prepare(metaData, headers);
			for(Field<?> field : fields)
				field.prepare(headers);
		}
	}
	
	@Override
	public T map(ResultSet rs) throws Exception {
		T obj = type.newInstance();
		for(Field<?> field : fields){
			Object value = field.map(rs);
			obj.set(field.getName(), value);
		}
		return obj;
	}
	
	@Override
	public void write(StreamWriter sw, T obj) throws Exception {
		sw.startObject(type.getSimpleName());
		for(Field field : fields)
			field.write(sw, obj.get(field.getName()));
		sw.endObject();
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
			Field<Object> field = new SimpleProperty<Object>(header.getColumnName());
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
