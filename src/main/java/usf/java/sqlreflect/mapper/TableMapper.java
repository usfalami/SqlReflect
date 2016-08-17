package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.field.Table;
import usf.java.sqlreflect.stream.StreamWriter;

public class TableMapper implements Mapper<Table> {

	@Override
	public Table map(ResultSet rs, int row) throws Exception {
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
			ColumnTableMapper cm = new ColumnTableMapper();
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
