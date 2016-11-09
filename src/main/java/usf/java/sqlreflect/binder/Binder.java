package usf.java.sqlreflect.binder;

import java.sql.SQLException;
import java.sql.Statement;

public interface Binder<T> {
	
	void bind(Statement stmt, T item) throws SQLException;
	
	void post(Statement stmt, T item) throws SQLException;
	
}
