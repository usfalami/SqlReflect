package usf.java.sqlreflect.parser;

import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.sql.Runnable;

public interface SqlParser {
	
	Server getServer();
	
	Runnable parseSQL(String sql);

}
