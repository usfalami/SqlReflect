package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

public class SimpleProperty<T> extends Field<T> {
	
	private String columnName;

	@Override
	public T get(ResultSet rs) throws Exception {
		return rs.getObject(columnName, type);
	}

}
