package usf.java.sqlreflect.mapper;

import usf.java.sqlreflect.mapper.filter.DefaultConverter;
import usf.java.sqlreflect.mapper.filter.ResultConverter;

public class Filter {
	
	public static final ResultConverter<?> DEFAULT_VALUE_CONVERTER = new DefaultConverter();

	private String columnName;
	private String propertyName;
	private ResultConverter<?> converter;
	
	public Filter(String columnName){
		this(columnName, columnName, DEFAULT_VALUE_CONVERTER);
	}
	public Filter(String columnName, String propertyName){
		this(columnName, propertyName, DEFAULT_VALUE_CONVERTER);
	}
	public Filter(String columnName, ResultConverter<?> converter){
		this(columnName, columnName, converter);
	}
	
	public Filter(String columnName, String propertyName, ResultConverter<?> converter) {
		this.columnName = columnName;
		this.propertyName = propertyName;
		this.converter = converter;
	}
	
	public String getColumnName() {
		return columnName;
	}
	public Filter setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public Filter setPropertyName(String propertyName) {
		this.propertyName = propertyName;
		return this;
	}
	public ResultConverter<?> getConverter() {
		return converter;
	}
	public Filter setConverter(ResultConverter<?> converter) {
		this.converter = converter;
		return this;
	}
	
}
