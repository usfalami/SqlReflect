package usf.java.sqlreflect.field;

import java.util.Objects;

public class Column extends Header {

	protected String type;
	
	protected Column() {
		super();
	}
	
	public Column(String name, String valueType, int size, String type) {
		super(name, valueType, size, null);
		this.type = type;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null) return false; 
		if(!arg0.getClass().equals(this.getClass())) return false;
		Column c = (Column)arg0;
		return Objects.equals(name, c.name) && 
				Objects.equals(valueType, c.valueType) &&
				Objects.equals(type, c.type) && 
				Objects.equals(size, c.size); 
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + valueType + ", clazz=" + clazz
				+ ", type=" + type + ", size=" + size + "]";
	}

}
