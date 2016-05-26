package usf.tera.reflect.executor;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.tera.reflect.AbstractReflect;
import usf.tera.reflect.adpter.ExecutorAdapter;

public class Executor<T extends ExecutorAdapter> extends AbstractReflect<T> {
	
	public final void exec(String query, Serializable... params) throws SQLException {
		if(query == null || query.isEmpty()) return;
		try {
			PreparedStatement s = null;
			try {
				ResultSet rs = null;
				try {
					s = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					if(params != null)
						for(int i=0; i<params.length; i++)
							s.setObject(i+1, params[i]);
					adapter.beforeExec(s);
					rs = s.executeQuery();
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
				if(s!=null) s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) con.close();
		}
	}

}
