package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.item.Argument;
import usf.java.sqlreflect.item.Procedure;
import usf.java.sqlreflect.reflect.scanner.ProcedureTypes;
import usf.java.sqlreflect.stream.StreamWriter;

public class ProcedureMapper implements Mapper<Procedure> {

	@Override
	public Procedure map(ResultSet rs, int row) throws Exception {
		Procedure p = new Procedure();
		p.setDatabaseName(rs.getString("PROCEDURE_SCHEM"));
		p.setName(rs.getString("PROCEDURE_NAME"));
		p.setType(ProcedureTypes.values()[rs.getInt("PROCEDURE_TYPE")].toString());
		return p;
	}

	@Override
	public void write(StreamWriter writer, Procedure procedure) throws Exception {
		writer.startObject("PROCEDURE");
		writer.writeString("PROCEDURE_SCHEM", procedure.getDatabaseName());
		writer.writeString("PROCEDURE_NAME", procedure.getName());
		writer.writeString("PROCEDURE_TYPE", procedure.getType());
		if(procedure.getColumns() != null){
			ColumnProcedureMapper cm = new ColumnProcedureMapper();
			writer.startList("COLUMNS");
			for(Argument c : procedure.getColumns())
				cm.write(writer, c);
			writer.endList();
		}
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"PROCEDURE_SCHEM", "PROCEDURE_NAME", "PROCEDURE_TYPE"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
		
	}
}
