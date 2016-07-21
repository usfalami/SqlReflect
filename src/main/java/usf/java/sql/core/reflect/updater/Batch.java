package usf.java.sql.core.reflect.updater;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.connection.transcation.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Reflector;

public class Batch extends Reflector {
	
	private Query[] query;

	public Batch(ConnectionManager cm) {
		super(cm);
	}

	public Batch set(String... sql) {
		query = new Query[sql.length];
		for(int i=0; i<sql.length; i++)
			query[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}

	public void run() throws SQLException, AdapterException {
		TransactionManager tm = null;
		try {
			tm = getConnectionManager().getTransactionManager();
			Statement stmt = null;
			try {
				
				tm.startTransaction();
				stmt = tm.buildBatch(query);
				int[] count = stmt.executeBatch();
				tm.endTransaction();

				System.out.println(Arrays.toString(count));
				
			} catch (SQLException e) {
				throw e;
			}
			finally {
				System.out.println(stmt.getMoreResults());
				getConnectionManager().close(stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			tm.rollback();
			throw e;
		}
		finally {
			getConnectionManager().close(tm);
		}
	}
	

}
