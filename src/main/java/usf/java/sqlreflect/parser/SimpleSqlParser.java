package usf.java.sqlreflect.parser;

import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.sql.Query;

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
