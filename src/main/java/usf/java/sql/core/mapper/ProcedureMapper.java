package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Procedure;
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
		writer.startObject("PROCEDURE");
		writer.writeString("PROCEDURE_SCHEM", procedure.getDatabase());
		writer.writeString("PROCEDURE_NAME", procedure.getName());
		if(procedure.getColumns() != null){
			ProcedureColumnMapper cm = new ProcedureColumnMapper();
			writer.startList("COLUMNS");
			for(Column c : procedure.getColumns())
				cm.write(writer, c);
			writer.endList();
		}
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"PROCEDURE_SCHEM", "PROCEDURE_NAME"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
		
	}
}
