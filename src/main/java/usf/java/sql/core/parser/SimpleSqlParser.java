package usf.java.sql.core.parser;

import usf.java.sql.core.field.Callable;
import usf.java.sql.core.server.Server;

public class SimpleSqlParser implements SqlParser {
	
	protected Server server;
	
	public SimpleSqlParser(Server server){
		this.server = server;
	}
	
	@Override
	public Server getServer() {
		return server;
	}
	
	@Override
	public Callable parseSQL(String sql) {
		Callable obj = server.parseCallable(sql);
		if(obj == null) obj = server.parseQuery(sql);
		return obj;
	}

}
