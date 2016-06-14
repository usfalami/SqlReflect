package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.db.field.Callable;

public class ProcedureMapper implements BeanMapper<Callable> {

	@Override
	public Callable map(ResultSet rs, int row) throws SQLException {
		return new Callable(
				rs.getString("PROCEDURE_SCHEM"), 
				rs.getString("PROCEDURE_NAME"));
				//rs.getString("PROCEDURE_TYPE")
	}

}
