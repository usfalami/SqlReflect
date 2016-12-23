package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryMapperHandler;
import usf.java.sqlreflect.mapper.GenericMapper;
import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class TableMapper extends GenericMapper<Table> {
	
	public TableMapper() {
		super(Table.class, new EntryMapperHandler<Table>(), SqlConstants.TABLE_NAME, SqlConstants.TABLE_TYPE);
	}
	
	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		return super.prepare(rs, type);
	}

}
