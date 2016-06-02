package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.adapter.Adapter;
import usf.java.field.SQL;

public interface ExecutorAdapter extends Adapter {
	
	void beforeConnecion();
	void afterConnecion();
	
	void beforeStatement();
	void afterStatement();
	
	void beforeExec(SQL sql) throws SQLException ;
	void afterExec(SQL sql, ResultSet rs) throws SQLException ;
	
	public static class Utils {
		
		public static int rowsCount(ResultSet rs) throws SQLException{
			int count = 0;
			if(rs.next()){
				rs.last();
				count=rs.getRow();
				rs.beforeFirst();
			}
			return count;
		}
	}

}
