package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ProcedureTypes;

public class ProcedureMapper extends SimpleObjectMapper<Procedure> {
	
	public ProcedureMapper() {
		super(new GenericTypeTemplate<Procedure>(Procedure.class, SqlConstants.PROCEDURE_NAME));
	}
	
	@Override
	public Template<Procedure> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.PROCEDURE_DATABASE));
		appendProperty(new SimpleProperty<String>(SqlConstants.PROCEDURE_TYPE, 
				new LabelIndexConverter<ProcedureTypes>(ProcedureTypes.class)
			));
		return super.prepare(rs, type);
	}

}
