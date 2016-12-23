package usf.java.sqlreflect.mapper.filter;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.reflect.Utils;

public class MetadataConverter extends Metadata {
	
	private ResultConverter<?> converter;
	
	public MetadataConverter(String columnName, ResultConverter<?> converter) {
		super(columnName);
		this.converter = converter;
	}
	
	public MetadataConverter(String columnName, String propertyName, ResultConverter<?> converter) {
		super(columnName, propertyName);
		this.converter = converter;
	}
	
	public ResultConverter<?> getConverter() {
		return converter;
	}
	public void setConverter(ResultConverter<?> converter) {
		this.converter = converter;
	}

	@Override
	public Object get(ResultSet rs) throws SQLException {
		return converter.convert(super.get(rs));
	}
	
	@Override
	public Metadata setColumnClassName(String columnClassName) {
		try {
			columnClassName = Utils.methodeType(converter.getClass()).getName();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			super.setColumnClassName(columnClassName);
		}
		return this;
	}

}
