package usf.java.sql.db.field;

public abstract class ComplexSQL implements SQL {
	
	protected String name, database, type, sql, parameters[];
	
	public ComplexSQL(String exec) {
		this.sql = exec;
	}
	
	public ComplexSQL(String database, String name, String type){
		this.database=database;
		this.name=name;
	}	
	
	@Override
	public String getQuery() {
		return sql;
	}
	@Override
	public void setSql(String sql) {
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
