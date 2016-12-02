package usf.java.sqlreflect.mapper.filter;

import usf.java.sqlreflect.reflect.Utils;

public class MapperFilter {
	
	private String columnName;
	private String mappedName;
	private ValueConverter<?> valueConverter;
	
	public MapperFilter(String columnName, String mappedName, ValueConverter<?> valueConverter) {
		this.columnName = columnName;
		this.mappedName = Utils.isEmptyString(mappedName) ? columnName : mappedName;
		this.valueConverter = Utils.isNull(valueConverter) ? new DefaultTransformer() : valueConverter;
	}
	public MapperFilter(String columnName, ValueConverter<?> valueConverter) {
		this(columnName, null, valueConverter);
	}
	public MapperFilter(String columnName, String mappedName) {
		this(columnName, mappedName, null);
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
