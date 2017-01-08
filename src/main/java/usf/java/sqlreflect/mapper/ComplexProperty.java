package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public class ComplexProperty<T> extends Template<T> {

	protected List<Template<?>> fields;
	
	public ComplexProperty(String name) {
		this(name, null);
	}
	public ComplexProperty(Class<T> type) {
		this(null, type);
	}
	public ComplexProperty(String name, Class<T> type) {
		super(name, type);
		this.fields = new ArrayList<Template<?>>();
	}

	public void setFields(List<Template<?>> fields) {
		this.fields = fields;
	}
	@Override
	public List<Template<?>> getFields() {
		return fields;
	}
	
	@Override
	protected void prepare(Map<String, Header> headers) throws Exception {
		for(Template<?> field : fields){
			field.setAccessorsFrom(type);
			field.prepare(headers);
		}
	}
	
	@Override
	public T map(ResultSet rs) throws Exception {
		T obj = type.newInstance();
		for(Template<?> field : fields){
			Object value = field.map(rs);
			field.setValue(obj, value);
		}
		return obj;
	}
	
	@Override
	public void write(StreamWriter sw, T obj) throws Exception {
		sw.startObject(type.getSimpleName());
		for(Template field : fields)
			field.write(sw, field.getValue(obj));
		sw.endObject();
	}
	
}
