package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Column;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.stream.StreamWriter;

public class ColumnMapper extends AdvancedEntryMapper<Column> {
	
	public ColumnMapper() {
		super(Column.class, SqlConstants.COLUMNS_COLUMNS);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE);
		super.prepare(rs, type);
	}

	@Override
	public void write(StreamWriter writer, Column parameter) throws Exception {
		writer.startObject("COLUMN");
		writer.writeString(SqlConstants.DATABASE_NAME, parameter.getDatabaseName());
		writer.writeString(SqlConstants.TABLE_NAME, parameter.getTableName());
		writer.writeString(SqlConstants.COLUMN_NAME, parameter.getName());
		writer.writeInt(SqlConstants.DATA_TYPE, parameter.getDataType());
		writer.writeString(SqlConstants.TYPE_NAME, parameter.getDataTypeName());
		writer.writeInt(SqlConstants.COLUMN_SIZE, parameter.getSize());
		writer.endObject();
	}
	
}
