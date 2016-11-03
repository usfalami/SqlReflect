package usf.java.sqlreflect.sql;

import usf.java.sqlreflect.sql.type.ParameterTypes;

public class Parameter<T> {
	
	private String name;//TODO later, namedQuery
	private T value; //TODO see getObject(int columnIndex, Class<T> type)
	private ParameterTypes type;
	
	private int sqlType;
	
	public Parameter(int sqlType, T value) {
		this(sqlType, value, ParameterTypes.IN);
	}
	public Parameter(int sqlType, T value, ParameterTypes type) {
		this.sqlType = sqlType;
		this.value = value;
		this.type = type;
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
	public ParameterTypes getType() {
		return type;
	}
	public Parameter<T> setType(ParameterTypes type) {
		this.type = type;
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
		return "Parameter [name=" + name + ", value=" + value + ", type=" + type + ", sqlType=" + sqlType + "]";
	}

}
