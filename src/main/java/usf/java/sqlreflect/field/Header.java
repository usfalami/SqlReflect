package usf.java.sqlreflect.field;

public class Header implements Field {

	protected String name, valueType, className;
	protected int size, sqlType;
	
	public Header(){
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSqlType() {
		return sqlType;
	}
	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}

	public String getClassName() {
		return className;
	}
	public void setClassName(String clazz) {
		this.className = clazz;
	}
	
}
