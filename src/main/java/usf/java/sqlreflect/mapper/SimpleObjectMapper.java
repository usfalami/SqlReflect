package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.sql.type.DatabaseType;

public class SimpleObjectMapper<T> implements Mapper<T> {

	private Template<T> complexObject;

	public SimpleObjectMapper(Class<T> mappedClassName) {
		complexObject = new ObjectTemplate<T>(mappedClassName);
	}
	public SimpleObjectMapper(Template<T> complexObject) {
		this.complexObject = complexObject;
	}

	@Override
	public Template<T> prepare(ResultSet rs, DatabaseType type) throws Exception {
		complexObject.prepare(rs.getMetaData());
		return complexObject;
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		return complexObject.map(rs);
	}

	public void appendProperty(SimpleProperty<?> property) {
		complexObject.getFields().add(property);
	}
}
