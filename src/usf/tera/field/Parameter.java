package usf.tera.field;

public class Parameter implements Field {

	protected String name, type, value, clazz;
	protected int index, size;
	
	public Parameter(int index, String value) {
		this.value = value;
		this.index = index;
	}

	public Parameter(int index, String name, String type, int size) {
		this(index, name, type, size, null);
	}
	
	public Parameter(int index, String name, String type, int size, String value) {
		this.name = name;
		this.index = index;
		this.type = type;
		this.size = size;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}

}
