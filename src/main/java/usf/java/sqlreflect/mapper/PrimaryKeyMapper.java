package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.item.PrimaryKey;
import usf.java.sqlreflect.stream.StreamWriter;

public class PrimaryKeyMapper implements Mapper<PrimaryKey> {

	@Override
	public PrimaryKey map(ResultSet rs, int row) throws Exception {
		PrimaryKey pk = new PrimaryKey();
		pk.setDatabaseName(rs.getString(SqlConstants.TABLE_SCHEM));
		pk.setTableName(rs.getString(SqlConstants.TABLE_NAME));
		pk.setColumnName(rs.getString(SqlConstants.COLUMN_NAME));
		pk.setName(rs.getString(SqlConstants.PK_NAME));
		pk.setKeySequence(rs.getInt(SqlConstants.KEY_SEQ));
		return pk;
	}

	@Override
	public void write(StreamWriter writer, PrimaryKey primaryKey) throws Exception {
		writer.startObject("PRIMARY_KEY");
		writer.writeString(SqlConstants.TABLE_SCHEM, primaryKey.getDatabaseName());
		writer.writeString(SqlConstants.TABLE_NAME, primaryKey.getTableName());
		writer.writeString(SqlConstants.COLUMN_NAME, primaryKey.getName());
		writer.writeString(SqlConstants.PK_NAME, primaryKey.getName());
		writer.writeInt(SqlConstants.KEY_SEQ, primaryKey.getKeySequence());
		writer.endObject();		
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.TABLE_SCHEM, SqlConstants.TABLE_NAME, SqlConstants.COLUMN_NAME, SqlConstants.PK_NAME, SqlConstants.KEY_SEQ};
	}

	@Override
	public void setColumnNames(String... columnNames) {
		
	}
	
	

}
