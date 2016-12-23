package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryBuilder;
import usf.java.sqlreflect.mapper.GenericMapper;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.mapper.filter.MetadataConverter;
import usf.java.sqlreflect.sql.entry.Argument;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ParameterTypes;

public class ArgumentMapper extends GenericMapper<Argument> {
	
	public ArgumentMapper() {
		super(Argument.class, new EntryBuilder(),
				SqlConstants.PROCEDURE_NAME, SqlConstants.COLUMN_NAME, 
				SqlConstants.DATA_TYPE, SqlConstants.TYPE_NAME, SqlConstants.LENGTH);
	}
	
	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(new Metadata(type.PROCEDURE_DATABASE, SqlConstants.DATABASE_NAME));
		addFilter(new MetadataConverter(
				SqlConstants.COLUMN_TYPE, 
				new LabelIndexConverter<ParameterTypes>(ParameterTypes.class)));
		return super.prepare(rs, type);
	}

}
