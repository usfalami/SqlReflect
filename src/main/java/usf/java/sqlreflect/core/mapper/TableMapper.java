package usf.java.sqlreflect.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.core.field.Column;
import usf.java.sqlreflect.core.field.Table;
import usf.java.sqlreflect.core.stream.StreamWriter;

public class TableMapper implements Mapper<Table> {

	@Override
	public Table map(ResultSet rs, int row) throws SQLException {
		return new Table(
				rs.getString("TABLE_SCHEM"), 
				rs.getString("TABLE_NAME"));
	}

	@Override
	public void write(StreamWriter writer, Table table) throws Exception {
		writer.startObject("TABLE");
		writer.writeString("TABLE_SCHEM", table.getDatabase());
		writer.writeString("TABLE_NAME", table.getName());
		if(table.getColumns() != null){
			TableColumnMapper cm = new TableColumnMapper();
			writer.startList("COLUMNS");
			for(Column c : table.getColumns())
				cm.write(writer, c);
			writer.endList();
		}
		writer.endObject();
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{"TABLE_SCHEM", "TABLE_NAME"};
	}

	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
		
	}

}
