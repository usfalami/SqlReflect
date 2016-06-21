package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.reflect.exception.AdapterException;
import usf.java.sql.core.stream.StreamWriter;

public class ProcedureMapper implements Mapper<Procedure> {

	@Override
	public Procedure map(ResultSet rs, int row) throws SQLException {
		return new Procedure(
				rs.getString("PROCEDURE_SCHEM"), 
				rs.getString("PROCEDURE_NAME"));
				//rs.getString("PROCEDURE_TYPE")
	}

	@Override
	public void write(StreamWriter writer, Procedure procedure) throws Exception {
		try {
			writer.startObject("PROCEDURE_SCHEM", "PROCEDURE_NAME");
			writer.writeString("PROCEDURE_SCHEM", procedure.getDatabase());
			writer.writeString("PROCEDURE_NAME", procedure.getName());
			//write columns
			writer.endObject();
		}catch(Exception e){
			e.printStackTrace();
			throw new AdapterException(e);
		}
	}
}
