package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
	
	public T map(ResultSet rs, int row) throws SQLException;

}