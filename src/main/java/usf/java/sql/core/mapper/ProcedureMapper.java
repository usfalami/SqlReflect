package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Procedure;

public class ProcedureMapper implements BeanMapper<Procedure> {

	@Override
	public Procedure map(ResultSet rs, int row) throws SQLException {
		return new Procedure(
				rs.getString("PROCEDURE_SCHEM"), 
				rs.getString("PROCEDURE_NAME"));
				//rs.getString("PROCEDURE_TYPE")
	}

}
