package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.sql.type.DatabaseType;

public interface Mapper<T> {

	ComplexObject<T> prepare(ResultSet rs, DatabaseType type) throws Exception;

	T map(ResultSet rs, int row) throws Exception;

}