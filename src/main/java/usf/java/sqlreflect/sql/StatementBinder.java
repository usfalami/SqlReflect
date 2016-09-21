package usf.java.sqlreflect.sql;

import java.sql.PreparedStatement;

public interface StatementBinder<T> {
	
	void bind(PreparedStatement pstmt, T item);

}
