package usf.java.sqlreflect.sql.entry.data;

import usf.java.sqlreflect.sql.entry.Entry;

public class Row extends Entry implements Data {
	
	@Override
	public void set(String field, Object value) {
		super.set(field, value);
	}
	
	public Object get(String field){
		return super.get(field);
	}
	
}
