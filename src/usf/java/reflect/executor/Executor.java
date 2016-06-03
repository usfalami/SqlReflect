package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.adapter.executor.ExecutorAdapter;
import usf.java.field.SQL;
import usf.java.reflect.AbstractReflect;

public class Executor<T extends ExecutorAdapter> extends AbstractReflect<T> {
		
	public void exec(SQL sql, Serializable... parameters) throws SQLException {
		Connection cnx = null;
		try {
			adapter.beforeConnecion();
			cnx = rf.newConnection();
			adapter.afterConnecion();
			
			PreparedStatement ps = null;
			try {
				
				adapter.beforeStatement();
				ps = cnx.prepareStatement(sql.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				if(parameters != null)
					for(int i=0; i<parameters.length; i++)
						ps.setObject(i+1, parameters[i]);
				adapter.afterStatement();
				
				ResultSet rs = null;
				try {
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
			rf.CloseConnection(cnx);
		}
	}

}
