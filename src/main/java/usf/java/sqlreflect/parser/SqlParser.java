package usf.java.sqlreflect.parser;

import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.server.Server;

public interface SqlParser {
	
	Server getServer();
	
	Query parseSQL(String sql);

}
