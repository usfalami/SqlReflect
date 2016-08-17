package usf.java.sqlreflect.reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ReflectorUtils {
	
	public static final String[] columnNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rm = rs.getMetaData();
		String[] columns = new String[rm.getColumnCount()];
		for(int i=0; i<columns.length; i++)
			columns[i] = rm.getColumnName(i+1);
		return columns;
	}
	
	public static int rowsCount(ResultSet rs) throws SQLException{
		int count = 0;
		if(rs.next()){
			rs.last();
			count=rs.getRow();
			rs.beforeFirst();
		}
		return count;
	}
	
	public static int columnsCount(ResultSet rs) throws SQLException {
		return rs.getMetaData().getColumnCount();
	}
	
	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
	}
	
}
