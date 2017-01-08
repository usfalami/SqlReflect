package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.sql.entry.GenericType;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public class GenericTypeTemplate<T extends GenericType> extends Template<T> {
	
	protected List<Template<?>> fields;
	
	public GenericTypeTemplate(Class<T> type, String... propertiesNames) {
		super(null, type);
		fields = new ArrayList<Template<?>>();
		if(!Utils.isEmptyArray(propertiesNames))
			for(String propertyName : propertiesNames)
				fields.add(new SimpleProperty<T>(propertyName));
	}

	@Override
	protected void prepare(Map<String, Header> headers) throws Exception {
		if(Utils.isEmptyCollection(fields)){
			Collection<Header> values = headers.values();
			for(Header header : values){
				Class<T> clazz = (Class<T>) Class.forName(header.getColumnClassName());
				fields.add(new SimpleProperty<T>(header.getColumnName(), clazz));
			}
		}
		for(Template<?> field : fields)
			field.prepare(headers);
	}
	
	@Override
	public T map(ResultSet rs) throws Exception {
		T obj = type.newInstance();
		for(Template<?> field : fields){
			Object value = field.map(rs);
			obj.set(field.getName(), value);
		}
		return obj;
	}
	
	@Override
	public void write(StreamWriter sw, T obj) throws Exception {
		sw.startObject(type.getSimpleName());
		for(Template field : fields)
			field.write(sw, obj.get(field.getName()));
		sw.endObject();
	}

	@Override
	public List<Template<?>> getFields() {
		return fields;
	}

}
