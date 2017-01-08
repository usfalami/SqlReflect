package usf.java.sqlreflect.sql.entry;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GenericType {
	
	private Map<String, Object> fields;
	
	public GenericType() {
		fields = new HashMap<String, Object>();
	}
	
	public GenericType set(String key, Object value) {
		fields.put(key, value);
		return this;
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
	
	public Map<String, Object> getFields() {
		return fields;
	}

}
