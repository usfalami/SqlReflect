package usf.java.sqlreflect.mapper.tmp;

public class MapperFilter {
	
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
		this(columnName, mappedName, new DefaultTransformer());
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
