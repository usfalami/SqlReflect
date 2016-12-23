package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.GenericMapper;
import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.mapper.builder.EntryBuilder;
import usf.java.sqlreflect.sql.entry.Column;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class ColumnMapper extends GenericMapper<Column> {
	
	public ColumnMapper() {
		super(Column.class, new EntryBuilder(),
				SqlConstants.TABLE_NAME, SqlConstants.COLUMN_NAME, SqlConstants.DATA_TYPE,
				SqlConstants.TYPE_NAME, SqlConstants.COLUMN_SIZE);
	}
	
	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(new Metadata(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME));
		return super.prepare(rs, type);
	}

}
