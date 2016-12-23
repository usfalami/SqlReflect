package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryMapperHandler;
import usf.java.sqlreflect.mapper.GenericMapper;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ProcedureTypes;

public class ProcedureMapper extends GenericMapper<Procedure> {
	
	public ProcedureMapper() {
		super(Procedure.class, new EntryMapperHandler<Procedure>(), SqlConstants.PROCEDURE_NAME);
	}
	
	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.PROCEDURE_DATABASE, SqlConstants.DATABASE_NAME);
		addFilter(SqlConstants.PROCEDURE_TYPE, new LabelIndexConverter<ProcedureTypes>(ProcedureTypes.class));
		return super.prepare(rs, type);
	}

}