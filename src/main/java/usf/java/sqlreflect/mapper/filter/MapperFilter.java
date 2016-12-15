package usf.java.sqlreflect.mapper.filter;

public class MapperFilter {
	
	private static final ValueConverter<?> DEFAULT_VALUE_CONVERTER = new DefaultConverter();
	
	private String columnName;
	private String propertyName;
	private ValueConverter<?> valueConverter;
	
	public MapperFilter(String columnName, String propertyName, ValueConverter<?> valueConverter) {
		this.columnName = columnName;
		this.propertyName = propertyName;
		this.valueConverter = valueConverter;
	}
	public MapperFilter(String columnName, ValueConverter<?> valueConverter) {
		this(columnName, columnName, valueConverter);
	}
	public MapperFilter(String columnName, String propertyName) {
		this(columnName, propertyName, DEFAULT_VALUE_CONVERTER);
	}
	public MapperFilter(String columnName) {
		this(columnName, columnName, DEFAULT_VALUE_CONVERTER);
	}

	public String getColumnName() {
		return columnName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public ValueConverter<?> getValueConverter() {
		return valueConverter;
	}
	
}
