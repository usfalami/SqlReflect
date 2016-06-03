package usf.java.reflect;

import java.sql.Connection;
import java.sql.SQLException;

public class Manager {
	
	public void CloseConnection(Connection cnx) throws SQLException {
		if(cnx==null)return;
		cnx.close();
	}

}
