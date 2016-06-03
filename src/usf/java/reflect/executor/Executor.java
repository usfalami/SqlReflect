package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.field.SQL;
import usf.java.reflect.Reflector;

public class Executor implements Reflector<ExecutorAdapter> {
		
	public void run(ExecutorAdapter adapter) throws SQLException {
		
		SQL sql = adapter.getSQL();
		Serializable[] parameters = adapter.getParametters();
		
		Connection cnx = null;
		try {
			adapter.beforeConnecion();
			cnx = adapter.getRf().newConnection();
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
			adapter.getRf().CloseConnection(cnx);
		}
	}

}
