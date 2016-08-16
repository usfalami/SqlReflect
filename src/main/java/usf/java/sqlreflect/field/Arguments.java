package usf.java.sqlreflect.field;

import java.io.Serializable;

public class Arguments implements Field {
	
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
