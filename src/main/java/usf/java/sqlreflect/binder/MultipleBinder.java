package usf.java.sqlreflect.binder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.sql.SQLException;
import java.sql.Statement;

public class MultipleBinder<T> implements Binder<T> {
	
	private String current;

	@Override
	public void bind(Statement stmt, T item) throws SQLException {

	}

	@Override
	public void post(Statement stmt, T item) throws SQLException {
		// TODO Auto-generated method stub

	}


	@Target(ElementType.METHOD)
	public static @interface QueryName {

		String name = "";

	}

}
