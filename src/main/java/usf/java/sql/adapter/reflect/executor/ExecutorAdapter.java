package usf.java.sql.adapter.reflect.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.reflect.executor.Performer.HasExecutorPerformer;

public interface ExecutorAdapter extends HasExecutorPerformer {

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
		public static int columnsCount(ResultSet rs) throws SQLException {
			return rs.getMetaData().getColumnCount();
		}

	}

}
