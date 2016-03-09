package usf.tera.executor;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import usf.tera.reflect.Reflect;

public abstract class Executor extends Reflect {
	
	protected static final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy : HH:mm:ss");
	
	public final void exec(String query, Serializable... params) throws SQLException {
		if(query == null || query.isEmpty()) return;
		PreparedStatement s = null;
		try {
			try {
				s = con.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				rootLog("Statement created");
				if(params != null)
					for(int i=0; i<params.length; i++)
						s.setObject(i+1, params[i]);
				rootLog("Statement prepared");
				beforeExec(s);
				ResultSet rs = s.executeQuery();
				afterExec(rs);
				rs.close();
				rootLog("Resultset closed");
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(s!=null) s.close();
				rootLog("Statement closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) con.close();
			rootLog("Connection closed");
		}
	}
	
	protected abstract void beforeExec(PreparedStatement s);
	protected abstract void afterExec(ResultSet rs);
	


}
