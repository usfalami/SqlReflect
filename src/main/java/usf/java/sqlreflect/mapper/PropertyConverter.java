package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.reflect.Utils;

public class PropertyConverter extends Property {
	
	private Converter<?> converter;
	
	public PropertyConverter(String columnName, Converter<?> converter) {
		super(columnName);
		this.converter = converter;
	}
	
	public PropertyConverter(String name, String columnName, Converter<?> converter) {
		super(name, columnName);
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
	public String getClassName() {
		String className = null;
		try {
			className = Utils.converterReturnType(converter.getClass()).getName();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			className = super.getClassName();
		}
		return className;
	}
}
