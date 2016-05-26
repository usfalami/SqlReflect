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
		PreparedStatement s = null;
		try {
			try {
				s = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				if(params != null)
					for(int i=0; i<params.length; i++)
						s.setObject(i+1, params[i]);
				adapter.beforeExec(s);
				ResultSet rs = s.executeQuery();
				adapter.afterExec(rs);
				rs.close();
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
