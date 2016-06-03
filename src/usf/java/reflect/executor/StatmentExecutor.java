package usf.java.reflect.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.reflect.Reflector;

public class StatmentExecutor implements Reflector<ExecutorAdapter> {
		
	public void run(ExecutorAdapter adapter) throws SQLException {
		
		String sql = adapter.getSQL();
		
		Connection cnx = null;
		try {
			adapter.beforeConnecion();
			cnx = adapter.getRf().newConnection();
			adapter.afterConnecion();
			
			Statement ps = null;
			try {
				
				adapter.beforeStatement();
				ps = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				adapter.afterStatement();
				
				ResultSet rs = null;
				try {
					adapter.beforeExec();
					rs = ps.executeQuery(sql);
					adapter.afterExec(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					if(rs!=null) rs.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null) ps.close();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			adapter.getRf().CloseConnection(cnx);
		}
	}

}
