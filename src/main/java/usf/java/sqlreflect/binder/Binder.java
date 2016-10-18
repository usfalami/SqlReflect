package usf.java.sqlreflect.binder;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Binder<T> {
	
	void bindPreparedStatement(PreparedStatement pstmt, T item) throws SQLException;

	void bindCallableStatement(CallableStatement cstmt, T item) throws SQLException;

	void updateOutParameter(CallableStatement cstmt, T item) throws SQLException;
	
}
