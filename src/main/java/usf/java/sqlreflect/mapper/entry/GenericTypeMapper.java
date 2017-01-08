package usf.java.sqlreflect.mapper.entry;

import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.sql.entry.GenericType;

public class GenericTypeMapper extends SimpleObjectMapper<GenericType> {

	public GenericTypeMapper() {
		super(new GenericTypeTemplate<GenericType>(GenericType.class));
	}

}
