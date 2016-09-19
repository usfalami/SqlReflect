package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.item.Argument;
import usf.java.sqlreflect.item.Table;
import usf.java.sqlreflect.stream.StreamWriter;

public class TableMapper implements Mapper<Table> {

	@Override
	public Table map(ResultSet rs, int row) throws Exception {
		Table t = new Table();
		t.setDatabaseName(rs.getString("TABLE_SCHEM"));
		t.setName(rs.getString("TABLE_NAME"));
		t.setType(rs.getString("TABLE_TYPE"));
		return t;
	}

	@Override
	public void write(StreamWriter writer, Table table) throws Exception {
		writer.startObject("TABLE");
		writer.writeString("TABLE_SCHEM", table.getDatabaseName());
		writer.writeString("TABLE_NAME", table.getName());
		writer.writeString("TABLE_TYPE", table.getType());
		//TODO : Update this
		if(table.getColumns() != null){
			ColumnTableMapper cm = new ColumnTableMapper();
			writer.startList("COLUMNS");
			for(Argument c : table.getColumns())
				cm.write(writer, c);
			writer.endList();
		}
		writer.endObject();
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{"TABLE_SCHEM", "TABLE_NAME", "TABLE_TYPE"};
	}

	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
	}

}
