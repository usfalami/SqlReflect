package usf.tera.reflect.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.tera.adpter.executor.ExecutorAdapter;
import usf.tera.field.SQL;

public class Executor<T extends ExecutorAdapter> extends AbstractExcecutor<T> {
		
	public void exec(SQL sql) throws SQLException {
		Connection cnx = null;
		try {
			cnx = rf.newConnection();
			PreparedStatement ps = null;
			try {
				ResultSet rs = null;
				try {
					ps = cnx.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					if(sql.getParametersToBing() != null)
						for(int i=0; i<sql.getParametersToBing().length; i++)
							ps.setObject(i+1, sql.getParametersToBing()[i]);
						adapter.beforeExec(sql);
						rs = ps.executeQuery();
						adapter.afterExec(sql, rs);
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
