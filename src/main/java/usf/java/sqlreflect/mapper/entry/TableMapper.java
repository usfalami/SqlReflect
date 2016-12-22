package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryPropertyMapper;
import usf.java.sqlreflect.mapper.FiltredMapper;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class TableMapper extends FiltredMapper<Table> {
	
	public TableMapper() {
		super(Table.class, new EntryPropertyMapper<Table>(), SqlConstants.TABLE_NAME, SqlConstants.TABLE_TYPE);
	}
	
	@Override
	public List<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		return super.prepare(rs, type);
	}

}
