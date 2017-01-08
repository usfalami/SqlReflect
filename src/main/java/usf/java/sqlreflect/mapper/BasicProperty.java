package usf.java.sqlreflect.mapper;

import usf.java.sqlreflect.stream.StreamWriter;

public class BasicProperty<T> extends SimpleProperty<T> {
	
	public BasicProperty(String columnName, Class<T> type) {
		super(columnName, type);
	}

	@Override
	public void write(StreamWriter sw, T obj) throws Exception {
		sw.startObject(type.getSimpleName());
		super.write(sw, obj);
		sw.endObject();
	}

}
