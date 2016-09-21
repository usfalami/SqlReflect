package usf.java.sqlreflect.bender;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import usf.java.sqlreflect.sql.Parameter;

public interface Binder<T> {
	
	void bind(PreparedStatement pstmt, T item) throws SQLException;
	
	public static class Utils {
		
		public static void set(PreparedStatement pstmt, int index, Parameter<?> arg) throws SQLException {
			if(arg == null) pstmt.setNull(index, Types.NULL); //TODO check that
			else if(arg.getValue() == null) pstmt.setNull(index, arg.getSqlType());
			else pstmt.setObject(index, arg.getValue(), arg.getSqlType());
		}
		
	}

}
