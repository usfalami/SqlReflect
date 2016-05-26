package usf.tera.reflect.adpter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExecutorColumnAdapter implements ExecutorAdapter {

	@Override
	public void beforeExec(PreparedStatement s) {
		System.out.println("List of columns : \n");
	}
	
	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		ResultSetMetaData md;
		try {
			md = rs.getMetaData();
			int count = md.getColumnCount();
			System.out.print(CADRE+COLUMN+"\n"+CADRE);
			
			for(int i=1; i<=count; i++) {
				System.out.format(PARAM_FORMAT+"\n", 
						i,
						md.getColumnName(i),
						md.getColumnTypeName(i),
						md.getColumnDisplaySize(i));
			}
			System.out.print(CADRE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
