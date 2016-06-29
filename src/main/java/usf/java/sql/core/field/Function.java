package usf.java.sql.core.field;

import java.util.List;

public abstract class Function implements Callable {
	
	protected String name, database, sql, parameters[];
	protected List<Parameter> columns;
	
	protected Function(){
		
	}
	
	public Function(String sql) {
		this.sql = sql;
	}
	
	public Function(String database, String name){
		this.database=database;
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
	public String getDatabase() {
		return database;
	}
	@Override
	public void setDatabase(String database) {
		this.database = database;
	}
	
	@Override
	public String[] getParameters() {
		return parameters;
	}
	@Override
	public void setParameters(String ...parameters) {
		this.parameters = parameters;
	}
	

	public List<Parameter> getColumns() {
		return columns;
	}
	public void setColumns(List<Parameter> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return sql;
	}
	
	
}
