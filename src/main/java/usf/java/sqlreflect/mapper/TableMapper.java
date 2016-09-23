package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.item.Column;
import usf.java.sqlreflect.sql.item.Table;
import usf.java.sqlreflect.stream.StreamWriter;

public class TableMapper implements Mapper<Table> {

	@Override
	public Table map(ResultSet rs, int row) throws Exception {
		Table t = new Table();
		t.setDatabaseName(rs.getString(SqlConstants.TABLE_SCHEM));
		t.setName(rs.getString(SqlConstants.TABLE_NAME));
		t.setType(rs.getString(SqlConstants.TABLE_TYPE));
		return t;
	}

	@Override
	public void write(StreamWriter writer, Table table) throws Exception {
		writer.startObject("TABLE");
		writer.writeString(SqlConstants.TABLE_SCHEM, table.getDatabaseName());
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
		return new String[]{SqlConstants.TABLE_SCHEM, SqlConstants.TABLE_NAME, SqlConstants.TABLE_TYPE};
	}

	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
	}

}
