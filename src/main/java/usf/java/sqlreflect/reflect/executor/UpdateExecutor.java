package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.bender.Binder;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public class UpdateExecutor extends AbstractExecutor<Integer> {

	private Runnable query;

	public UpdateExecutor(TransactionManager cm) {
		super(cm);
	}
	
	public UpdateExecutor set(String sql) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		return this;
	}
	
	protected <P> void run(Adapter<Integer> adapter, P args, Binder<P> binder, TimePerform tp) throws Exception {
		Statement stmt = null;
		try {

			ActionPerform action = tp.startAction(Constants.ACTION_STATEMENT);
			stmt = getConnectionManager().buildStatement(query, args, binder);
			action.end();

			action = tp.startAction(Constants.ACTION_EXECUTION);
			int rows = getConnectionManager().executeUpdate(stmt, query, args, binder);
			action.end();

			action = tp.startAction(Constants.ACTION_ADAPT);
			adapter.adapte(rows, 1);
			action.end();
			
			tp.setRowCount(rows);
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(stmt);
		}
	}

	
	public <P> void run(Adapter<Integer> adapter, P args, Binder<P> binder) throws Exception {
		TimePerform tp = new TimePerform();
		ActionPerform total = tp.startAction(Constants.ACTION_TOTAL);
		try {
			adapter.start();
			TransactionManager tm = getConnectionManager();
			adapter.prepare(null);
			if(tm.isTransacting())
				run(adapter, args, binder, tp);
			else {
				try {

					ActionPerform action = tp.startAction(Constants.ACTION_CONNECTION);
					tm.startTransaction();
					action.end();
					run(adapter, args, binder, tp);
					tm.endTransaction();
				} catch (Exception e) {
					tm.rollback();
					throw e;
				}
				finally {
					tm.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			total.end();
			adapter.end(tp);
		}
	}

	@Override
	public void run(Adapter<Integer> adapter) throws Exception {
		this.run(adapter, null, null);
	}
	
}
