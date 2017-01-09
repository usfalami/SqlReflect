package usf.java.sqlreflect.mapper.generic;

import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.sql.entry.GenericType;

public class GenericTypeMapper<T extends GenericType> extends SimpleObjectMapper<T> {

	public GenericTypeMapper(Class<T> clazz, String... columnsNames) {
		super(new GenericTypeTemplate<T>(clazz, columnsNames));
	}

}
