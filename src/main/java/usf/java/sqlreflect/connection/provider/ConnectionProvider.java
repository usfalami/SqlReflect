package usf.java.sqlreflect.connection.provider;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {
	
	Connection getConnection() throws SQLException;
	
}
