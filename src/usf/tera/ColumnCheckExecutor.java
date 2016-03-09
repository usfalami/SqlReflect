package usf.tera;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.tera.executor.Executor;

public class ColumnCheckExecutor extends Executor {

	@Override
	protected void beforeExec(PreparedStatement s) {
		
	}
	
	@Override
	protected void afterExec(ResultSet rs) {
		ResultSetMetaData md;
		try {
			md = rs.getMetaData();
			int count;
			log("Column count : " + (count = md.getColumnCount()));
			
			log(String.format("[%6s | %25s | %10s | %5s | %20s]", "Column","Name","Type","Size","Class"));
			
			for(int i=1; i<=count; i++) {
				log(String.format("[%6s | %25s | %10s | %5s | %20s]", 
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
