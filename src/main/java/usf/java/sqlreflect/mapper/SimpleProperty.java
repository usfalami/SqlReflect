package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.sql.entry.Header;

public class SimpleProperty<T> extends Field<T> {
	
	private String columnName;
	private Converter<? extends T> converter;
	
	private Generic<T> proxy;
	
	public SimpleProperty(String columnName) {
		super(columnName);
		this.columnName = columnName;
	}
	public SimpleProperty(String name, String columnName) {
		super(name);
		this.columnName = columnName;
	}
	public SimpleProperty(String columnName, Converter<? extends T> converter) {
		super(columnName);
		this.columnName = columnName;
		this.converter = converter;
	}
	public SimpleProperty(String name, String columnName, Converter<? extends T> converter) {
		super(name);
		this.columnName = columnName;
		this.converter = converter;
	}
	
	@Override
	public void prepare(Map<String, Header> headers) throws Exception {
		super.prepare(headers);
		if(Utils.isNull(type)){
			if(Utils.isNull(converter)) {
				String className = headers.get(columnName).getColumnClassName();
				type = (Class<T>) Class.forName(className);
			}
			else
				type = (Class<T>) Utils.converterReturnType(converter.getClass());
		}
		proxy = Utils.isNull(converter) ? new MapOnly() : new MapAndConvert();
	}

	@Override
	public T map(ResultSet rs) throws Exception {
		return proxy.map(rs);
	}
	
	private class MapAndConvert implements Generic<T> {
		@Override
		public T map(ResultSet rs) throws Exception {
			return converter.convert(rs.getObject(columnName));
		}
	}
	private class MapOnly implements Generic<T> {
		@Override
		public T map(ResultSet rs) throws Exception {
			return type.cast(rs.getObject(columnName));
		}
	}

}
