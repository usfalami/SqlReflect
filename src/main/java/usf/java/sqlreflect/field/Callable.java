package usf.java.sqlreflect.field;

import java.util.List;

public abstract class Callable implements Query {
	
	protected String name, databaseName, sql, parameters[];
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
	
	@Override
	public String getSQL() {
		return sql;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDatabaseName() {
		return databaseName;
	}
	@Override
	public void setDatabaseName(String database) {
		this.databaseName = database;
	}
	
	@Override
	public String[] getParameters() {
		return parameters;
	}
	@Override
	public void setParameters(String ...parameters) {
		this.parameters = parameters;
	}

	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return sql;
	}
	
	
}
