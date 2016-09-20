package usf.java.sqlreflect.item;

import java.util.List;

import usf.java.sqlreflect.sql.Runnable;

public abstract class Callable extends Entry implements Runnable {
	
	private String callable, parameters[];
	private List<Argument> arguments;
	
	public Callable(){
		
	}

	public Callable(String callable) {
		this.callable = callable;
	}
	
	public abstract String getDatabaseName();
	public abstract void setDatabaseName(String databaseName);

	public abstract String getName();
	public abstract void setName(String name);
	
	public abstract String getType();
	public abstract void setType(String type);

	public String[] getParameters() {
		return parameters;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public List<Argument> getArguments() {
		return arguments;
	}
	public void setArguments(List<Argument> arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public String asQuery() {
		return callable;
	}
	
}
