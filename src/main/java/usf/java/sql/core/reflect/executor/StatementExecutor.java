package usf.java.sql.core.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.db.field.SQL;

public class StatementExecutor implements Executor {

	@Override
	public void run(HasExecutor adapter, SQL sql, Serializable ... parametters) throws SQLException {
		
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
					adapter.preExec(sql);
					rs = ps.executeQuery(sql.get());
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
