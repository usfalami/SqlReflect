package usf.java.field;

public class Schema implements Field {
	
	protected String name;
	
	public Schema() {
		// TODO Auto-generated constructor stub
	}

	public Schema(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
