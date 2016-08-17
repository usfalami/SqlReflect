package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.field.Procedure;
import usf.java.sqlreflect.stream.StreamWriter;

public class ProcedureMapper implements Mapper<Procedure> {

	@Override
	public Procedure map(ResultSet rs, int row) throws Exception {
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
			ColumnProcedureMapper cm = new ColumnProcedureMapper();
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
