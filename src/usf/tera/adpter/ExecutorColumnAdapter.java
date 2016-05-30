package usf.tera.adpter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExecutorColumnAdapter implements ExecutorAdapter {

	@Override
	public void beforeExec(PreparedStatement s) {

	}
	
	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		Formatter f = new Formatter(System.out, COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_TYPE_LENGTH, COLUMN_SIZE_LENGTH);
		synchronized(System.out) {
			f.startTable();
			f.formatHeaders("N°", "Name", "Type", "Size"); 
			for(int i=1; i<=count; i++)
				f.formatRow(i,
						md.getColumnName(i),
						md.getColumnTypeName(i),
						md.getColumnDisplaySize(i));
			f.endTable();
		}
	}
}
