package usf.java.sqlreflect.parser;

import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.sql.Query;

public interface SqlParser {
	
	Server getServer();
	
	Query parseSQL(String sql);

}
