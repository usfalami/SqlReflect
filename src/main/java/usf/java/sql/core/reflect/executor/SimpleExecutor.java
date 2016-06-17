package usf.java.sql.core.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.field.Callable;

public class SimpleExecutor implements Executor {

	@Override
	public void run(HasExecutor adapter, Callable callable, Serializable ... parametters) throws SQLException {
		
		Connection cnx = null;
		try {
			adapter.preConnecion();
			cnx = adapter.getConnectionManager().newConnection();
			adapter.postConnecion();
			
			Statement stmt = null;
			try {
				
				adapter.preStatement();
				stmt = adapter.getConnectionManager().buildStatement(cnx, callable.getSQL(), parametters);
				adapter.postStatement();
				
				ResultSet rs = null;
				try {
					adapter.preExec(callable);
					rs = stmt instanceof Statement ? 
							stmt.executeQuery(callable.getSQL()) : ((PreparedStatement)stmt).executeQuery();
					adapter.postExec(callable, rs);
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
				adapter.getConnectionManager().close(stmt);
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
