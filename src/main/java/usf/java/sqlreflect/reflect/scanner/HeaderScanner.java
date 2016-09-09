package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Header;
import usf.java.sqlreflect.mapper.HeaderMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public class HeaderScanner extends AbstractDataScanner<Header> {
	
	public HeaderScanner(ConnectionManager cm) {
		super(cm);
	}

	@Override
	protected void run(Statement stmt, Adapter<Header> adapter, TimePerform tp) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = tp.startAction(Constants.ACTION_EXECUTION);
			rs = getConnectionManager().executeQuery(stmt, getCallable().getSQL());
			action.end();
			
			Mapper<Header> mapper = new HeaderMapper();
			ResultSetMetaData rm = rs.getMetaData();
			adapter.prepare(mapper);

			action = tp.startAction(Constants.ACTION_ADAPT);
			for(int i=1; i<=rm.getColumnCount(); i++) {
				Header col = mapper.map(rs, i);
				adapter.adapte(col, i);
			}
			action.end();
			tp.setRowCount(rm.getColumnCount());
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}

}
