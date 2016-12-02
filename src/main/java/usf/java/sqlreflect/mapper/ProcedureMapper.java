package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.filter.IndexEnumFilter;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Argument;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ProcedureTypes;
import usf.java.sqlreflect.stream.StreamWriter;

public class ProcedureMapper extends AdvancedEntryMapper<Procedure> {
	
	public ProcedureMapper() {
		super(Procedure.class, SqlConstants.PROCEDURE_COLUMNS);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addMapperFilter(SqlConstants.DATABASE_NAME, type.PROCEDURE_DATABASE);
		addMapperFilter(SqlConstants.PROCEDURE_TYPE, new IndexEnumFilter<ProcedureTypes>(ProcedureTypes.class));
		super.prepare(rs, type);
	}

	@Override
	public void write(StreamWriter writer, Procedure procedure) throws Exception {
		writer.startObject("PROCEDURE");
		writer.writeString(SqlConstants.DATABASE_NAME, procedure.getDatabaseName());
		writer.writeString(SqlConstants.PROCEDURE_NAME, procedure.getName());
		writer.writeString(SqlConstants.PROCEDURE_TYPE, procedure.getType());
		if(Utils.isNotNull(procedure.getArguments())){
			ArgumentMapper cm = new ArgumentMapper();
			writer.startList("COLUMNS");
			for(Argument c : procedure.getArguments())
				cm.write(writer, c);
			writer.endList();
		}
		writer.endObject();
	}
	
}
