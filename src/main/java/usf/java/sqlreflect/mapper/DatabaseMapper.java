package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.field.Database;
import usf.java.sqlreflect.stream.StreamWriter;

public class DatabaseMapper implements Mapper<Database> {

	@Override
	public Database map(ResultSet rs, int row) throws Exception {
		Database d = new Database();
		d.setName(rs.getString("TABLE_SCHEM"));
		return d;
	}

	@Override
	public void write(StreamWriter writer, Database database) throws Exception {
		writer.startObject("DATABASE");
		writer.writeString("TABLE_SCHEM", database.getName());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"TABLE_SCHEM"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		
	}

}
