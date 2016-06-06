package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.reflect.executor.adapter.ExecutorAdapter;

public class PreparedStatmentExecutor implements Executor {
		
	public void run(ExecutorAdapter adapter, String query, Serializable ... parameters) throws SQLException {
		
		Connection cnx = null;
		try {
			adapter.preConnecion();
			cnx = adapter.getConnectionManager().newConnection();
			adapter.postConnecion();
			
			PreparedStatement ps = null;
			try {
				
				adapter.preStatement();
				ps = cnx.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				if(parameters != null)
					for(int i=0; i<parameters.length; i++)
						ps.setObject(i+1, parameters[i]);
				adapter.postStatement();
				
				ResultSet rs = null;
				try {
					adapter.preExec();
					rs = ps.executeQuery();
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
