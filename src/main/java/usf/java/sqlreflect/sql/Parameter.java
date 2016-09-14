package usf.java.sqlreflect.sql;

public class Parameter<T> {
	
	private String name;//TODO later, namedQuery
	private T value; //TODO see getObject(int columnIndex, Class<T> type)
	private boolean out;
	
	private int sqlType;
	
	protected Parameter(int sqlType, T value) {
		this(sqlType, value, false, null);
	}
	public Parameter(int sqlType, T value, boolean out) {
		this(sqlType, value, out, null);
	}
	public Parameter(int sqlType, T value, boolean out, String name) {
		this.sqlType = sqlType;
		this.value = value;
		this.out = out;
		this.name = name;
	}
	
	public int getSqlType() {
		return sqlType;
	}
	public T getValue() {
		return value;
	}
	public Parameter<T> setValue(T value) {
		this.value = value;
		return this;
	}
	public boolean isOut() {
		return out;
	}
	public Parameter<T> setOut(boolean out) {
		this.out = out;
		return this;
	}
	public String getName() {
		return name;
	}
	public Parameter<T> setName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public String toString() {
		return "Parameter [name=" + name + ", value=" + value + ", out=" + out + ", sqlType=" + sqlType + "]";
	}


}
