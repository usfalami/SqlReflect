package usf.java.sql.core.field;

public class Database implements Field {
	
	protected String name;
	
	public Database() {
		// TODO Auto-generated constructor stub
	}

	public Database(String name) {
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
