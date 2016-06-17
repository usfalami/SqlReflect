package usf.java.sql.core.field;

public abstract class Function implements Callable, HasColumns {
	
	protected String name, database, sql, parameters[];
	protected Column[] columns;
	
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
	

	@Override
	public Column[] getColumns() {
		return columns;
	}
	@Override
	public void setColumns(Column... columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return sql;
	}
	
	
}
