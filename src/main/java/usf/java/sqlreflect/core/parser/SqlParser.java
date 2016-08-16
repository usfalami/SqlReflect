package usf.java.sqlreflect.core.parser;

import usf.java.sqlreflect.core.field.Query;
import usf.java.sqlreflect.core.server.Server;

public interface SqlParser {
	
	Server getServer();
	
	Query parseSQL(String sql);

}
