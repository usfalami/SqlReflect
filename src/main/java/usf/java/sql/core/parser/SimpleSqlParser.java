package usf.java.sql.core.parser;

import usf.java.sql.core.field.Query;
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
	public Query parseSQL(String sql) {
		Query obj = server.parseCallable(sql);
		if(obj == null) obj = server.parseQuery(sql);
		return obj;
	}

}
