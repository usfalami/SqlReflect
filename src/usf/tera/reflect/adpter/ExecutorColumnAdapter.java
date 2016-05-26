package usf.tera.reflect.adpter;

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
		ResultSetMetaData md;
		try {
			md = rs.getMetaData();
			int count = md.getColumnCount();
			System.out.print(CADRE+COLUMN+CADRE);
			for(int i=1; i<=count; i++)
				System.out.format(COLUMN_FORMAT, 
						i,
						md.getColumnName(i),
						md.getColumnTypeName(i),
						md.getColumnDisplaySize(i));
			System.out.print(CADRE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
