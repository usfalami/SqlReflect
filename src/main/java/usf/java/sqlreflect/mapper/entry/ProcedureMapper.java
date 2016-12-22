package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryPropertyMapper;
import usf.java.sqlreflect.mapper.FiltredMapper;
import usf.java.sqlreflect.mapper.filter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ProcedureTypes;

public class ProcedureMapper extends FiltredMapper<Procedure> {
	
	public ProcedureMapper() {
		super(Procedure.class, new EntryPropertyMapper<Procedure>(), SqlConstants.PROCEDURE_NAME);
	}
	
	@Override
	public List<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.PROCEDURE_DATABASE, SqlConstants.DATABASE_NAME);
		addFilter(SqlConstants.PROCEDURE_TYPE, new LabelIndexConverter<ProcedureTypes>(ProcedureTypes.class));
		return super.prepare(rs, type);
	}

}
