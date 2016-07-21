package usf.java.sql.core.reflect;

import java.io.Serializable;

public class Arguments {
	
	private Serializable[] args;
		
	public Arguments(Serializable... args) {
		this.args = args;
	}

	public void set(Serializable... args) {
		this.args = args;
	}

	public Serializable[] get() {
		return args;
	}

	public boolean isEmpty() {
		return args == null || args.length == 0;
	}
}
