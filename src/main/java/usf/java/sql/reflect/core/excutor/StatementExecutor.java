package usf.java.sql.reflect.core.excutor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.db.field.SQL;

public class StatementExecutor implements Executor {

	@Override
	public void run(Adapter adapter, SQL sql, Serializable ... parametters) throws SQLException {
		
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
					if(rs!=null) rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				if(ps!=null) ps.close();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().closeConnection(cnx);
		}
	}

}