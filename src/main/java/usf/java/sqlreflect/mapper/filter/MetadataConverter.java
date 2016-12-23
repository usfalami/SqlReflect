package usf.java.sqlreflect.mapper.filter;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.reflect.Utils;

public class MetadataConverter extends Metadata {
	
	private Converter<?> converter;
	
	public MetadataConverter(String columnName, Converter<?> converter) {
		super(columnName);
		this.converter = converter;
	}
	
	public MetadataConverter(String columnName, String propertyName, Converter<?> converter) {
		super(columnName, propertyName);
		this.converter = converter;
	}
	
	public Converter<?> getConverter() {
		return converter;
	}
	public void setConverter(Converter<?> converter) {
		this.converter = converter;
	}

	@Override
	public Object get(ResultSet rs) throws SQLException {
		return converter.convert(super.get(rs));
	}
	
	@Override
	public Metadata setColumnClassName(String columnClassName) {
		try {
			columnClassName = Utils.converterReturnType(converter.getClass()).getName();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			super.setColumnClassName(columnClassName);
		}
		return this;
	}

}
