package usf.java.sql.core.parser;

import usf.java.sql.core.field.Query;
import usf.java.sql.core.server.Server;

public interface SqlParser {
	
	Server getServer();
	
	Query parseSQL(String sql);

}
