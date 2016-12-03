package usf.java.sqlreflect.mapper.filter;

public class MapperFilter {
	
	private static final ValueConverter<?> DEFAULT_VALUE_CONVERTER = new DefaultConverter();
	
	private String columnName;
	private String mappedName;
	private ValueConverter<?> valueConverter;
	
	public MapperFilter(String columnName, String mappedName, ValueConverter<?> valueConverter) {
		this.columnName = columnName;
		this.mappedName = mappedName;
		this.valueConverter = valueConverter;
	}
	public MapperFilter(String columnName, ValueConverter<?> valueConverter) {
		this(columnName, columnName, valueConverter);
	}
	public MapperFilter(String columnName, String mappedName) {
		this(columnName, mappedName, DEFAULT_VALUE_CONVERTER);
	}
	public MapperFilter(String columnName) {
		this(columnName, columnName, DEFAULT_VALUE_CONVERTER);
	}

	public String getColumnName() {
		return columnName;
	}
	public String getMappedName() {
		return mappedName;
	}
	public ValueConverter<?> getValueConverter() {
		return valueConverter;
	}
	
}
