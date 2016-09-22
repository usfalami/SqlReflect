package usf.java.sqlreflect.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SqlUtils {
	
	public static final String[] columnNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rm = rs.getMetaData();
		String[] columns = new String[rm.getColumnCount()];
		for(int i=0; i<columns.length; i++)
			columns[i] = rm.getColumnName(i+1);
		return columns;
	}
}
