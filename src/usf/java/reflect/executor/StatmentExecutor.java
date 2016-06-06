package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.reflect.executor.adapter.ExecutorAdapter;

public class StatmentExecutor implements Executor {
		
	public void run(ExecutorAdapter adapter,  String query, Serializable ... parametters) throws SQLException {
		
		Connection cnx = null;
		try {
			adapter.preConnecion();
			cnx = adapter.getConnectionManager().newConnection();
			adapter.postConnecion();
			
			Statement ps = null;
			try {
				
				adapter.preStatement();
				ps = cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				adapter.postStatement();
				
				ResultSet rs = null;
				try {
					adapter.preExec();
					rs = ps.executeQuery(query);
					adapter.postExec(rs);
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
			adapter.getConnectionManager().closeConnection(cnx);
		}
	}

}
