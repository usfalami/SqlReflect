package usf.java.sqlreflect.sql.entry;

import java.util.Collection;

import usf.java.sqlreflect.sql.Runnable;

public abstract class Callable extends Entry implements Runnable {
	
	private String callable, parameters[];
	private Collection<Argument> arguments;
	
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

	public Collection<Argument> getArguments() {
		return arguments;
	}
	public void setArguments(Collection<Argument> arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public String asQuery() {
		return callable;
	}
	
}
