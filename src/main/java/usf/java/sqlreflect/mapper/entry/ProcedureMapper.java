package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.mapper.PropertyConverter;
import usf.java.sqlreflect.mapper.builder.EntryBuilder;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ProcedureTypes;

public class ProcedureMapper extends SimpleObjectMapper<Procedure> {
	
	public ProcedureMapper() {
		super(Procedure.class, new EntryBuilder(), 
				SqlConstants.PROCEDURE_NAME);
	}
	
	@Override
	public Collection<Property> prepare(ResultSet rs, DatabaseType type) throws Exception {
		addPropertyFilter(new Property(SqlConstants.DATABASE_NAME, type.PROCEDURE_DATABASE));
		addPropertyFilter(new PropertyConverter(SqlConstants.PROCEDURE_TYPE, 
				new LabelIndexConverter<ProcedureTypes>(ProcedureTypes.class)
			));
		return super.prepare(rs, type);
	}

}
