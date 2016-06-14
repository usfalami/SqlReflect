package usf.java.sql.core.field;

public abstract class Callable implements SQL {
	
	protected String name, database, sql, parameters[];
	
	public Callable(String sql) {
		this.sql = sql;
	}
	
	public Callable(String database, String name){
		this.database=database;
		this.name=name;
	}	
	
	@Override
	public String get() {
		return sql;
	}
	@Override
	public void set(String sql) {
		this.sql = sql;
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
	public String toString() {
		return sql;
	}
}
