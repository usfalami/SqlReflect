package usf.tera.adpter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExecutorResultAdapter implements ExecutorAdapter {

	@Override
	public void beforeExec(PreparedStatement s) {
		
	}

	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		Formatter f = new Formatter(System.out, count, VALUE_LENGTH);
		Object[] param = new Object[count]; 
		for(int i=1; i<=count; i++) param[i-1]=md.getColumnName(i);
		synchronized(System.out) {
			f.startTable();
			f.formatHeaders(param);
			while(rs.next()){
				for(int i=1; i<=count; i++) param[i-1]=rs.getObject(i);
				f.formatRow(param);
			}
			f.endTable();
		}
	}

}
