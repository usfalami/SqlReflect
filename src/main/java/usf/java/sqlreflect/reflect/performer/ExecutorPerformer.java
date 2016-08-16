package usf.java.sqlreflect.reflect.performer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import usf.java.sqlreflect.adapter.PerformAdapter;
import usf.java.sqlreflect.adapter.TimePerformAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ReflectorUtils;

public class ExecutorPerformer extends AbstractReflector implements Performer {
	
	private Query query;
	private Arguments args;
	
	public ExecutorPerformer(ConnectionManager cm) {
		super(cm);
	}
	
	public ExecutorPerformer set(String sql, Serializable ... parameters){
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = new Arguments(parameters);
		return this;
	}
	
	@Override
	public void run(PerformAdapter adapter) throws Exception {
		adapter.start();
		Connection cnx = null;
		try {

			adapter.preConnecion();
			cnx = getConnectionManager().getConnection();
			adapter.postConnecion();
			
			Statement stmt = null;
			try {
				
				adapter.preStatement();
				stmt = getConnectionManager().buildStatement(cnx, query, args);
				adapter.postStatement();
				
				ResultSet rs = null;
				try {
			
					adapter.preExec();
					rs = getConnectionManager().executeQuery(stmt, query.getSQL());
					adapter.postExec(ReflectorUtils.rowsCount(rs));
					
				} catch (Exception e) {
					throw e;
				}
				finally {
					getConnectionManager().close(rs);
				}
			} catch (Exception e) {
				throw e;
			}
			finally {
				getConnectionManager().close(stmt);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close(cnx);
			adapter.end();
		}
	}

	@Override
	public TimePerform run() throws Exception {
		TimePerformAdapter tpa = new TimePerformAdapter();
		this.run(tpa);
		return tpa.getTimePerform();
	}

}
