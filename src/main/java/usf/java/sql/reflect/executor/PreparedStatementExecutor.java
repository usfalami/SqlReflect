package usf.java.sql.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.db.field.SQL;
import usf.java.sql.reflect.executor.adapter.ExecutorAdapter;

public class PreparedStatementExecutor implements Executor {

	@Override
	public void run(ExecutorAdapter adapter, SQL sql, Serializable ... parameters) throws SQLException {
		
		Connection cnx = null;
		try {
			adapter.preConnecion();
			cnx = adapter.getConnectionManager().newConnection();
			adapter.postConnecion();
			
			PreparedStatement ps = null;
			try {
				
				adapter.preStatement();
				ps = cnx.prepareStatement(sql.get(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				if(parameters != null)
					for(int i=0; i<parameters.length; i++)
						ps.setObject(i+1, parameters[i]);
				adapter.postStatement();
				
				ResultSet rs = null;
				try {
					adapter.preExec(sql);
					rs = ps.executeQuery();
					adapter.postExec(sql, rs);
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
				finally {
					if(rs!=null) rs.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				if(ps!=null) ps.close();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().closeConnection(cnx);
		}
	}

}
