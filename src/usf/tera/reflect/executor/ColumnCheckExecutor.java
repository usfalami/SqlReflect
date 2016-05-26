package usf.tera.reflect.executor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.tera.reflect.adpter.ExecutorAdapter;

public class ColumnCheckExecutor implements ExecutorAdapter {

	@Override
	public void beforeExec(PreparedStatement s) {
		
	}
	
	@Override
	public void afterExec(ResultSet rs) {
		ResultSetMetaData md;
		try {
			md = rs.getMetaData();
			int count;
			System.out.println("Column count : " + (count = md.getColumnCount()));
			
			System.out.println(String.format("[%6s | %25s | %10s | %5s | %20s]", "Column","Name","Type","Size","Class"));
			
			for(int i=1; i<=count; i++) {
				System.out.println(String.format("[%6s | %25s | %10s | %5s | %20s]", 
						i,
						md.getColumnName(i),
						md.getColumnTypeName(i),
						md.getColumnDisplaySize(i),
						md.getColumnClassName(i)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
