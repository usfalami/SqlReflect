package usf.java.sql.db.field;

public abstract class ComplexSQL implements SQL {
	
	protected String name, schema, type, sql, parameters[];
	protected int[] bindableParameters;
	
	public ComplexSQL(String exec) {
		this.sql = exec;
	}
	
	public ComplexSQL(String schema, String name, String type){
		this.schema=schema;
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
	public String getSchema() {
		return schema;
	}
	@Override
	public void setSchema(String schema) {
		this.schema = schema;
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
	
	public void setBindableParameters(int[] bindableParameters) {
		this.bindableParameters = bindableParameters;
	}
	public int[] getBindableParameters() {
		return bindableParameters;
	}
	
	@Override
	public String toString() {
		return sql;
	}
}
