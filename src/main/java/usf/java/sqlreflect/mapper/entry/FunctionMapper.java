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
import usf.java.sqlreflect.sql.entry.Function;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.FunctionTypes;

public class FunctionMapper extends GenericMapper<Function> {
	
	public FunctionMapper() {
		super(Function.class, new EntryBuilder(),
				SqlConstants.FUNCTION_NAME);
	}
	
	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(new Metadata(type.FUNCTION_DATABASE, SqlConstants.DATABASE_NAME));
		addFilter(new MetadataConverter(
				SqlConstants.FUNCTION_TYPE, 
				new LabelIndexConverter<FunctionTypes>(FunctionTypes.class)
			));
		return super.prepare(rs, type);
	}

}
