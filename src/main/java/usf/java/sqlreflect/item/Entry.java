package usf.java.sqlreflect.item;

import java.util.HashMap;
import java.util.Map;

public abstract class Entry implements Item {
	
	private Map<String, Object> fields;
	
	public Entry() {
		fields = new HashMap<String, Object>();
	}
	
	protected String getString(String key){
		return (String) fields.get(key);
	}
	protected Integer getInteger(String key){
		return (Integer) fields.get(key);
	}
	protected Object getObject(String key){
		return fields.get(key);
	}
	protected void set(String key, Object value) {
		fields.put(key, value);
	}
}
