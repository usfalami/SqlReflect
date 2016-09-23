package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public class BatchExecutor extends AbstractExecutor<Integer> {

	private Runnable[] queries;

	public BatchExecutor(TransactionManager cm) {
		super(cm);
	}

	public BatchExecutor set(String... sql) {
		queries = new Runnable[sql.length];
		for(int i=0; i<sql.length; i++)
			queries[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}
	
	protected <P> void run(Adapter<Integer> adapter, Collection<P> argsList, Binder<P> binder, TimePerform tp) throws Exception {
		Statement stmt = null; //TODO : Check query
		try {
			
			TransactionManager tm = getConnectionManager();

			ActionPerform action = tp.startAction(Constants.ACTION_STATEMENT);
			stmt = queries.length > 1 || argsList == null || argsList.isEmpty() ? tm.buildBatch(queries) : tm.buildBatch(queries[0], argsList, binder);
			action.end();
			
			action = tp.startAction(Constants.ACTION_EXECUTION);
			int[] rows = stmt.executeBatch();
			action.end();
			
			action = tp.startAction(Constants.ACTION_ADAPT);
			for(int i=0; i<rows.length; i++)
				adapter.adapte(rows[i], i+1);
			action.end();
			
			tp.setRowCount(Utils.sum(rows));

		}finally {
			getConnectionManager().close(stmt);
		}
	}

	//duplicated code
	public <P> void run(Adapter<Integer> adapter, Collection<P> argsList, Binder<P> binder) throws Exception {
		TimePerform tp = new TimePerform();
		ActionPerform total = tp.startAction(Constants.ACTION_TOTAL);
		try {
			adapter.start();
			TransactionManager tm = getConnectionManager();
			adapter.prepare(null);
			if(tm.isTransacting())
				run(adapter, argsList, binder, tp);
			else {
				try {

					ActionPerform action = tp.startAction(Constants.ACTION_CONNECTION);
					tm.startTransaction();
					action.end();
					run(adapter, argsList, binder, tp);
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


	@Override
	public List<Integer> run() throws Exception {
		return this.run(null, null);
	}
	
	public <P> List<Integer> run(Collection<P> argsList, Binder<P> binder) throws Exception {
		ListAdapter<Integer> adapter = new ListAdapter<Integer>();
		this.run(adapter, argsList, binder);
		return adapter.getList();
	}

}
