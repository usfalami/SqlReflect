package usf.java.sqlreflect.mapper.generic;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ProcedureTypes;

public class ProcedureMapper extends GenericTypeMapper<Procedure> {
	
	public ProcedureMapper() {
		super(Procedure.class, 
				SqlConstants.PROCEDURE_NAME);
	}
	
	@Override
	public Template<Procedure> prepare(ResultSet rs, DatabaseType type) throws Exception {
		Converter<String> converter = new LabelIndexConverter<ProcedureTypes>(ProcedureTypes.class);
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.PROCEDURE_DATABASE));
		appendProperty(new SimpleProperty<String>(SqlConstants.PROCEDURE_TYPE, converter));
		return super.prepare(rs, type);
	}

}
