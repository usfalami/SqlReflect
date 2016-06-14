package usf.java.sql.core.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.db.field.SQL;

public class PreparedStatementExecutor implements Executor {

	@Override
	public void run(HasExecutor adapter, SQL sql, Serializable ... parameters) throws SQLException {
		
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
					adapter.getConnectionManager().close(rs);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				adapter.getConnectionManager().close(ps);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(cnx);
		}
	}

}
