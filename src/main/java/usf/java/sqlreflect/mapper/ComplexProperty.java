package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.sql.entry.Header;

public class ComplexProperty<T> extends Field<T> {

	protected List<Field<?>> fields;
	
	public ComplexProperty(String name) {
		super(name);
		this.fields = new ArrayList<Field<?>>();
	}
	public ComplexProperty(String name, Class<T> type) {
		super(name, type);
		this.fields = new ArrayList<Field<?>>();
	}
	public ComplexProperty(String name, List<Field<?>> fields) {
		super(name);
		this.fields = fields;
	}
	public ComplexProperty(String name, Class<T> type, List<Field<?>> fields) {
		super(name, type);
		this.fields = fields;
	}

	public void setFields(List<Field<?>> fields) {
		this.fields = fields;
	}
	public List<Field<?>> getFields() {
		return fields;
	}
	
	@Override
	protected void prepare(Map<String, Header> headers) throws Exception {
		super.prepare(headers);
		for(Field<?> field : fields){
			field.setAccessors(type);
			field.prepare(headers);
		}
	}
	
	@Override
	public T map(ResultSet rs) throws Exception {
		T obj = type.newInstance();
		for(Field<?> field : fields){
			Object value = field.map(rs);
			field.setValue(obj, value);
		}
		return obj;
	}
	
}
