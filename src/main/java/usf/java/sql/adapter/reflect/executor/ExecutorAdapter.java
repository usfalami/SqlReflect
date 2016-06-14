package usf.java.sql.adapter.reflect.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.reflect.executor.Executor;
import usf.java.sql.core.reflect.executor.PreparedStatementExecutor;
import usf.java.sql.core.reflect.executor.StatementExecutor;
import usf.java.sql.core.reflect.executor.Executor.HasExecutor;

public interface ExecutorAdapter extends HasExecutor {

	void execute(String query, Serializable... parameters) throws SQLException;

	public static class Utils {

		public static Executor executorFor(Serializable... parameters) {
			return parameters==null || parameters.length==0 ? new StatementExecutor() : new PreparedStatementExecutor();
		}

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
