package usf.java.sqlreflect.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.mapper.Field;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.stream.StreamWriter;

public class EntryWriter implements Writer<Entry> {
	
	private Map<String, WriterTypes> map;
	
	public EntryWriter() {
	}

	@Override
	public void prepare(Template<? extends Entry> complexObject) {
		map = new HashMap<String, WriterTypes>();
		List<Field<?>> fields = complexObject.getFields();
		for(Field<?> field : fields) {
			WriterTypes tw = WriterTypes.writerfor(field.getClass().getName());
			map.put(field.getName(), tw);
		}
	}

	@Override
	public void write(StreamWriter writer, Entry obj) throws Exception {
		for(java.util.Map.Entry<String, WriterTypes> entry : map.entrySet()){
			WriterTypes type = entry.getValue();
			String name = entry.getKey();
			type.write(writer, name, obj.get(name));
		}
	}

}