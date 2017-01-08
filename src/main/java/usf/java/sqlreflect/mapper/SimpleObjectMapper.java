package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.sql.type.DatabaseType;

public class SimpleObjectMapper<T> implements Mapper<T> {

	private ComplexObject<T> complexObject;

	public SimpleObjectMapper(Class<T> mappedClassName) {
		complexObject = new ComplexObject<T>(mappedClassName);
	}

	@Override
	public ComplexObject<T> prepare(ResultSet rs, DatabaseType type) throws Exception {
		complexObject.prepare(rs.getMetaData());
		return complexObject;
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		return complexObject.get(rs);
	}

	public void appendProperty(SimpleProperty<?> property) {
		complexObject.getFields().add(property);
	}
}
