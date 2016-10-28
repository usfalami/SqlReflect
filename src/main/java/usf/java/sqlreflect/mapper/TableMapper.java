package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Column;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.stream.StreamWriter;

public class TableMapper extends AbstractItemMapper<Table> {
	
	@Override
	public Table map(ResultSet rs, int row) throws Exception {
		Table t = new Table();
		t.setDatabaseName(rs.getString(getServerConstants().TABLE_DATABASE));
		t.setName(rs.getString(SqlConstants.TABLE_NAME));
		t.setType(rs.getString(SqlConstants.TABLE_TYPE));
		return t;
	}

	@Override
	public void write(StreamWriter writer, Table table) throws Exception {
		writer.startObject("TABLE");
		writer.writeString(SqlConstants.DATABASE_NAME, table.getDatabaseName());
		writer.writeString(SqlConstants.TABLE_NAME, table.getName());
		writer.writeString(SqlConstants.TABLE_TYPE, table.getType());
		//TODO : Update this
		if(table.getColumns() != null){
			ColumnMapper cm = new ColumnMapper();
			writer.startList("COLUMNS");
			for(Column c : table.getColumns())
				cm.write(writer, c);
			writer.endList();
		}
		writer.endObject();
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.DATABASE_NAME, SqlConstants.TABLE_NAME, SqlConstants.TABLE_TYPE};
	}

}
