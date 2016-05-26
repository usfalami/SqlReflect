package usf.tera.reflect.adpter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExecutorResultAdapter implements ExecutorAdapter {
	
	protected static final int size = -15;
	protected static final String FORMAT = "%"+size+"s";

	@Override
	public void beforeExec(PreparedStatement s) {
		
	}

	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		while(rs.next()){
			for(int i=1; i<=count; i++)
				System.out.format(FORMAT, rs.getObject(i));
			System.out.println();
		}
	}
}
