package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.reflect.SqlUtils;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class SimpleMapper<T> implements Mapper<T> {
	
	private Class<T> mappedClass;
	private String[] columnNames;
	private List<Header> headers;
	private PropertyMapper<T> propertyMapper;

	public SimpleMapper(Class<T> clazz, PropertyMapper<T> propertyMapper, String... selectedColumnNames) {
		this.mappedClass = clazz;
		this.propertyMapper = propertyMapper;
		this.columnNames = selectedColumnNames;
	}

	@Override
	public List<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		headers = Utils.isEmptyArray(columnNames) ?
			SqlUtils.allColumn(rs) : SqlUtils.columns(rs, columnNames);
		propertyMapper.prepare(headers);
		return headers;
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = getMappedClass().newInstance();
		for(Header header : headers) {
			Object value = rs.getObject(header.getColumnName());
			propertyMapper.setProperty(item, header.getPropertyName(), value);
		}
		return item;
	}
	@Override
	public Class<T> getMappedClass() {
		return mappedClass;
	}
	
}
