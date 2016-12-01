package usf.java.sqlreflect.mapper.tmp;

public class ColumnTransformer {
	
	private String columnName;
	private String mappedName;
	private ValueConverter<?> valueConverter;
	
	public ColumnTransformer(String columnName, String mappedName, ValueConverter<?> valueConverter) {
		this.columnName = columnName;
		this.mappedName = mappedName;
		this.valueConverter = valueConverter;
	}
	public ColumnTransformer(String columnName, ValueConverter<?> valueConverter) {
		this(columnName, columnName, valueConverter);
	}
	public ColumnTransformer(String columnName, String mappedName) {
		this(columnName, mappedName, new DefaultTransformer());
	}

	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getMappedName() {
		return mappedName;
	}
	public void setMappedName(String mappedName) {
		this.mappedName = mappedName;
	}

	public ValueConverter<?> getValueConverter() {
		return valueConverter;
	}
	public void setValueConverter(ValueConverter<?> valueConverter) {
		this.valueConverter = valueConverter;
	}
	
}
