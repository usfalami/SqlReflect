package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.ComplexObject;
import usf.java.sqlreflect.mapper.EntryProperty;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ProcedureTypes;

public class ProcedureMapper extends SimpleObjectMapper<Procedure> {
	
	public ProcedureMapper() {
		super(Procedure.class);
		appendProperty(new EntryProperty<String>(SqlConstants.PROCEDURE_NAME));
	}
	
	@Override
	public ComplexObject<Procedure> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new EntryProperty<String>(SqlConstants.DATABASE_NAME, type.PROCEDURE_DATABASE));
		appendProperty(new EntryProperty<String>(SqlConstants.PROCEDURE_TYPE, 
				new LabelIndexConverter<ProcedureTypes>(ProcedureTypes.class)
			));
		return super.prepare(rs, type);
	}

}
