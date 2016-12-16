package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.filter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ProcedureTypes;

public class ProcedureMapper extends AdvancedEntryMapper<Procedure> {
	
	public ProcedureMapper() {
		super(Procedure.class, 
				SqlConstants.PROCEDURE_NAME, SqlConstants.PROCEDURE_TYPE);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.PROCEDURE_DATABASE, SqlConstants.DATABASE_NAME);
		addFilter(SqlConstants.PROCEDURE_TYPE, new LabelIndexConverter<ProcedureTypes>(ProcedureTypes.class));
		super.prepare(rs, type);
	}

}
