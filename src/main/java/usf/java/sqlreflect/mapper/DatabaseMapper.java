package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Database;
import usf.java.sqlreflect.stream.StreamWriter;

public class DatabaseMapper extends AbstractItemMapper<Database> {
	
	@Override
	public Database map(ResultSet rs, int row) throws Exception {
		Database d = new Database();
		d.setName(rs.getString(getServerConstants().TABLE_DATABASE));
		return d;
	}

	@Override
	public void write(StreamWriter writer, Database database) throws Exception {
		writer.startObject("DATABASE");
		writer.writeString(SqlConstants.DATABASE_NAME, database.getName());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.DATABASE_NAME};
	}
	
}
