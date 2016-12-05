package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Column;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.stream.StreamWriter;

public class TableMapper extends AdvancedEntryMapper<Table> {
	
	public TableMapper() {
		super(Table.class, SqlConstants.TABLE_NAME, SqlConstants.TABLE_TYPE);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		super.prepare(rs, type);
	}

	@Override
	public void write(StreamWriter writer, Table table) throws Exception {
		writer.startObject("TABLE");
		writer.writeString(SqlConstants.DATABASE_NAME, table.getDatabaseName());
		writer.writeString(SqlConstants.TABLE_NAME, table.getName());
		writer.writeString(SqlConstants.TABLE_TYPE, table.getType());
		//TODO : Update this
		if(Utils.isNotNull(table.getColumnNames())){
			ColumnMapper cm = new ColumnMapper();
			writer.startList("COLUMNS");
			for(Column c : table.getColumnNames())
				cm.write(writer, c);
			writer.endList();
		}
		writer.endObject();
	}
	
}
