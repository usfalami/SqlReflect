package usf.java.sqlreflect.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.core.field.Database;
import usf.java.sqlreflect.core.stream.StreamWriter;

public class DatabaseMapper implements Mapper<Database> {

	@Override
	public Database map(ResultSet rs, int row) throws SQLException {
		return new Database(rs.getString("TABLE_SCHEM"));
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
