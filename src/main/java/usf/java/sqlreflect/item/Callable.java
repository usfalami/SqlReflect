package usf.java.sqlreflect.item;

import java.util.List;

import usf.java.sqlreflect.sql.Runnable;

public abstract class Callable implements Item, Runnable {
	
	protected String databaseName, name, sql, parameters[];
	protected List<Column> columns;
	
	public Callable(){
		
	}
	
	public Callable(String sql) {
		this.sql = sql;
	}
	
	public Callable(String database, String name){
		this.databaseName=database;
		this.name=name;
	}

	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String[] getParameters() {
		return parameters;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	@Override
	public String asQuery() {
		return sql;
	}
	
}
