package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.PrimaryKey;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.stream.StreamWriter;

public class PrimaryKeyMapper extends AdvancedEntryMapper<PrimaryKey> {
	
	public PrimaryKeyMapper() {
		super(PrimaryKey.class, SqlConstants.PRIMARY_KEY_COLUMNS);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		super.prepare(rs, type);
	}

	@Override
	public void write(StreamWriter writer, PrimaryKey primaryKey) throws Exception {
		writer.startObject("PRIMARY_KEY");
		writer.writeString(SqlConstants.DATABASE_NAME, primaryKey.getDatabaseName());
		writer.writeString(SqlConstants.TABLE_NAME, primaryKey.getTableName());
		writer.writeString(SqlConstants.COLUMN_NAME, primaryKey.getColumnName());
		writer.writeString(SqlConstants.PK_NAME, primaryKey.getName());
		writer.writeInt(SqlConstants.KEY_SEQ, primaryKey.getKeySequence());
		writer.endObject();		
	}

}
