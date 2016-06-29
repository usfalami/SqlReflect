package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Table;
import usf.java.sql.core.stream.StreamWriter;

public class TableMapper implements Mapper<Table> {

	@Override
	public Table map(ResultSet rs, int row) throws SQLException {
		return new Table(
				rs.getString("TABLE_SCHEM"), 
				rs.getString("TABLE_NAME"));
	}

	@Override
	public void write(StreamWriter writer, Table field) throws Exception {
		// TODO Auto-generated method stub
		
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
