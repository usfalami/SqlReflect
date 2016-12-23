package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryBuilder;
import usf.java.sqlreflect.mapper.GenericMapper;
import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.entry.PrimaryKey;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class PrimaryKeyMapper extends GenericMapper<PrimaryKey> {
	
	public PrimaryKeyMapper() {
		super(PrimaryKey.class, new EntryBuilder(),
				SqlConstants.TABLE_NAME, SqlConstants.COLUMN_NAME, 
				SqlConstants.PK_NAME, SqlConstants.KEY_SEQ);
	}
	
	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(new Metadata(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME));
		return super.prepare(rs, type);
	}

}
