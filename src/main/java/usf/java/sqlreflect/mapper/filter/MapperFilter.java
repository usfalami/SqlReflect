package usf.java.sqlreflect.mapper.filter;

public class MapperFilter {
	
	private static final ResultConverter<?> DEFAULT_VALUE_CONVERTER = new DefaultConverter();
	
	private String columnName;
	private String propertyName;
	private ResultConverter<?> valueConverter;
	
	public MapperFilter(String columnName, String propertyName, ResultConverter<?> valueConverter) {
		this.columnName = columnName;
		this.propertyName = propertyName;
		this.valueConverter = valueConverter;
	}
	public MapperFilter(String columnName, ResultConverter<?> valueConverter) {
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
	public ResultConverter<?> getValueConverter() {
		return valueConverter;
	}
	
}
