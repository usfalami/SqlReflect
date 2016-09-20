package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.item.Database;
import usf.java.sqlreflect.stream.StreamWriter;

public class DatabaseMapper implements Mapper<Database> {

	@Override
	public Database map(ResultSet rs, int row) throws Exception {
		Database d = new Database();
		d.setName(rs.getString(SqlConstants.TABLE_SCHEM));
		return d;
	}

	@Override
	public void write(StreamWriter writer, Database database) throws Exception {
		writer.startObject("DATABASE");
		writer.writeString(SqlConstants.TABLE_SCHEM, database.getName());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.TABLE_SCHEM};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		
	}

}
