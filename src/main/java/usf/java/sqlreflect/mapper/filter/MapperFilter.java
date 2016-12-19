package usf.java.sqlreflect.mapper.filter;

public class MapperFilter {
	
	public static final ResultConverter<?> DEFAULT_VALUE_CONVERTER = new DefaultConverter();
	
	private String columnName;
	private String propertyName;
	private ResultConverter<?> resultConverter;
	
	public MapperFilter(String columnName, String propertyName, ResultConverter<?> resultConverter) {
		this.columnName = columnName;
		this.propertyName = propertyName;
		this.resultConverter = resultConverter;
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
		return resultConverter;
	}
	
}
