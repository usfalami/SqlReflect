package usf.java.sqlreflect.sql.item;

public class Row extends Entry {
	
	@Override
	public void set(String field, Object value) {
		super.set(field, value);
	}
	
	public Object get(String field){
		return super.getObject(field);
	}
	
}
