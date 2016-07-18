package usf.java.sql.core.field;

public class Header implements Field {

	protected String name, valueType, clazz;
	protected int size;
	
	protected Header(){
		
	}
	
	public Header(String name, String valueType, int size, String clazz) {
		this.name = name;
		this.valueType = valueType;
		this.size = size;
		this.clazz = clazz;
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

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
}
