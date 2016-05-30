package usf.tera.field;

import java.io.Serializable;

public class Macro implements SQL {
	
	protected String name, schema, sql;
	protected Serializable[] parametersToBing;
	protected Parameter[] parameters;
	
	public Macro(String exec) {
		this.sql = exec;
	}
	
	public Macro(String schema, String name, Parameter[] parameter){
		this.schema=schema;
		this.name=name;
		this.parameters=parameter;
	}

	@Override
	public String getSql() {
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
	public Serializable[] getParametersToBing() {
		return parametersToBing;
	}
	@Override
	public void setParametersToBing(Serializable... parametersToBing) {
		this.parametersToBing=parametersToBing;
	}

	@Override
	public Parameter[] getParameters() {
		return parameters;
	}
	@Override
	public void setParameters(Parameter ...parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return sql;
	}
	
}
