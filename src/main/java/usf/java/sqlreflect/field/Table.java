package usf.java.sqlreflect.field;

import java.util.List;

public class Table implements Field {

	private String name, databaseName, type;
	private List<Column> columns;
	
	public Table() {

	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String database) {
		this.databaseName = database;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

}
