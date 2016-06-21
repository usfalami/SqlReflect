package usf.java.sql.core.parser;

import usf.java.sql.core.field.Callable;
import usf.java.sql.core.server.Server;

public interface SqlParser {
	
	Server getServer();
	
	Callable parseSQL(String sql);

}
