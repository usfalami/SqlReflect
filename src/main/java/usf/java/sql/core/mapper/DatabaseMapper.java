package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Database;
import usf.java.sql.core.stream.StreamWriter;

public class DatabaseMapper implements Mapper<Database> {

	@Override
	public Database map(ResultSet rs, int row) throws SQLException {
		return new Database(rs.getString("TABLE_SCHEM"));
	}

	@Override
	public void write(StreamWriter writer, Database database) throws Exception {
		writer.startObject("TABLE_SCHEM");
		writer.writeString("TABLE_SCHEM", database.getName());
		writer.endObject();
	}

}
