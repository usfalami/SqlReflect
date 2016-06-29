package usf.java.sql.core.field;

public class Table {

	protected String name, database;
	
	public Table() {

	}
	
	public Table(String database, String name) {
		this.database = database;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	
	

}
