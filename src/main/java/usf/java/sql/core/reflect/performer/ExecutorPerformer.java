package usf.java.sql.core.reflect.performer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.reflect.Reflector;
import usf.java.sql.core.reflect.ReflectorUtils;
import usf.java.sql.core.reflect.exception.AdapterException;

public class ExecutorPerformer extends Reflector implements Performer {
	
	public ExecutorPerformer(ConnectionManager cm) {
		super(cm);
	}

	public void run(PerformerAdapter adapter, Callable callable, Serializable ... parametters) throws SQLException, AdapterException {
		adapter.start();
		Connection cnx = null;
		try {
			adapter.preConnecion();
			cnx = cm.newConnection();
			adapter.postConnecion();
			
			Statement stmt = null;
			try {
				
				adapter.preStatement();
				stmt = cm.buildStatement(cnx, callable.getSQL(), parametters);
				adapter.postStatement();
				
				ResultSet rs = null;
				try {
					adapter.preExec();
					rs = stmt instanceof Statement ? 
							stmt.executeQuery(callable.getSQL()) : ((PreparedStatement)stmt).executeQuery();
					adapter.postExec(ReflectorUtils.rowsCount(rs));
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
				finally {
					cm.close(rs);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				cm.close(stmt);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(cnx);
			adapter.end();
		}
	}

}
