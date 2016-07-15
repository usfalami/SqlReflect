package usf.java.sql.core.field;

public class Header implements Field {

	protected String name;
	protected String type;
	protected int size;
	protected String clazz;
	
	public Header(String name, String type, int size, String clazz) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
		this.clazz = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
}
