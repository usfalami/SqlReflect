package usf.tera.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.tera.reflect.adpter.ExecutorAdapter;

public class Executor<T extends ExecutorAdapter> extends AbstractExcecutor<T> {
		
	public void exec(String query, Serializable... params) throws SQLException {
		if(query == null || query.isEmpty()) return;
		Connection cnx = null;
		try {
			cnx = rf.newConnection();
			PreparedStatement ps = null;
			try {
				ResultSet rs = null;
				try {
					ps = cnx.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					if(params != null)
						for(int i=0; i<params.length; i++)
							ps.setObject(i+1, params[i]);
						adapter.beforeExec(ps);
						rs = ps.executeQuery();
						adapter.afterExec(rs);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					if(rs!=null) rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null) ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(cnx!=null) cnx.close();
		}
	}

}
