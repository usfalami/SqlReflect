package usf.java.sqlreflect.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.mapper.ComplexObject;
import usf.java.sqlreflect.mapper.Field;
import usf.java.sqlreflect.stream.StreamWriter;

public class ObjectReflectWriter implements Writer<Object> {

	private ComplexObject<? extends Object> complexObject;
	private Map<String, WriterTypes> map;
	
	@Override
	public void prepare(ComplexObject<? extends Object> complexObject) {
		this.complexObject = complexObject;
		this.map = new HashMap<String, WriterTypes>();
		List<Field<?>> fields = complexObject.getFields();
		for(Field<?> field : fields) {
			String name = field.getName();
			WriterTypes tw = WriterTypes.writerfor(field.getClass().getName());
			map.put(name, tw);
		}
	}

	@Override
	public void write(StreamWriter writer, Object obj) throws Exception {
		List<Field<?>> fields = complexObject.getFields();
		for(Field<?> field : fields) {
			String name = field.getName();
			WriterTypes type = map.get(name);
			Object value = field.getValue(obj);
			type.write(writer, name, value);
		}
	}
	
}
