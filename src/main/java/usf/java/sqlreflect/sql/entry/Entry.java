package usf.java.sqlreflect.sql.entry;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {
	
	private Map<String, Object> fields;
	
	public Entry() {
		fields = new HashMap<String, Object>();
	}
	
	public void set(String key, Object value) {
		fields.put(key, value);
	}
	
	public Object get(String key){
		return fields.get(key);
	}	
	protected String getString(String key){
		return (String) fields.get(key);
	}
	protected Integer getInteger(String key){
		return (Integer) fields.get(key);
	}

}