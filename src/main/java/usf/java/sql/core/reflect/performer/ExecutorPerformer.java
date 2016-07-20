package usf.java.sql.core.reflect.performer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.adapter.TimePerformAdapter;
import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.reflect.Reflector;
import usf.java.sql.core.reflect.ReflectorUtils;

public class ExecutorPerformer extends Reflector implements Performer {
	
	private Query callable;
	private Serializable[] parameters;
	
	public ExecutorPerformer(ConnectionManager cm) {
		super(cm);
	}
	
	public ExecutorPerformer set(Query callable, Serializable ... parameters){
		this.callable = callable;
		this.parameters = parameters;
		return this;
	}
	
	@Override
	public void run(PerformAdapter adapter) throws SQLException, AdapterException {
		adapter.start();
		Connection cnx = null;
		try {

			adapter.preConnecion();
			cnx = getConnectionManager().getConnection();
			adapter.postConnecion();
			
			Statement stmt = null;
			try {
				
				adapter.preStatement();
				stmt = getConnectionManager().buildStatement(cnx, callable.getSQL(), parameters);
				adapter.postStatement();
				
				ResultSet rs = null;
				try {
			
					adapter.preExec();
					rs = getConnectionManager().executeQuery(stmt, callable.getSQL(), parameters);
					adapter.postExec(ReflectorUtils.rowsCount(rs));
					
				} catch (SQLException e) {
					throw e;
				}
				finally {
					getConnectionManager().close(rs);
				}
			} catch (SQLException e) {
				throw e;
			}
			finally {
				getConnectionManager().close(stmt);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close(cnx);
			adapter.end();
		}
	}

	@Override
	public TimePerform run() throws SQLException, AdapterException {
		TimePerformAdapter tpa = new TimePerformAdapter();
		this.run(tpa);
		return tpa.getTimePerform();
	}

}
