package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.item.Argument;
import usf.java.sqlreflect.item.Procedure;
import usf.java.sqlreflect.sql.ProcedureTypes;
import usf.java.sqlreflect.stream.StreamWriter;

public class ProcedureMapper implements Mapper<Procedure> {

	@Override
	public Procedure map(ResultSet rs, int row) throws Exception {
		Procedure p = new Procedure();
		p.setDatabaseName(rs.getString(SqlConstants.PROCEDURE_SCHEM));
		p.setName(rs.getString(SqlConstants.PROCEDURE_NAME));
		p.setType(ProcedureTypes.values()[rs.getInt(SqlConstants.PROCEDURE_TYPE)].toString());
		return p;
	}

	@Override
	public void write(StreamWriter writer, Procedure procedure) throws Exception {
		writer.startObject("PROCEDURE");
		writer.writeString(SqlConstants.PROCEDURE_SCHEM, procedure.getDatabaseName());
		writer.writeString(SqlConstants.PROCEDURE_NAME, procedure.getName());
		writer.writeString(SqlConstants.PROCEDURE_TYPE, procedure.getType());
		if(procedure.getArguments() != null){
			ArgumentMapper cm = new ArgumentMapper();
			writer.startList("COLUMNS");
			for(Argument c : procedure.getArguments())
				cm.write(writer, c);
			writer.endList();
		}
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.PROCEDURE_SCHEM, SqlConstants.PROCEDURE_NAME, SqlConstants.PROCEDURE_TYPE};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
		
	}
}
