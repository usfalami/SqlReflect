package usf.java.sql.db.field;

public class Function implements SQL {
	
	protected String name, database, type, sql, parameters[];
	
	public Function(String sql) {
		this.sql = sql;
	}
	
	public Function(String database, String name){
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	};
	
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