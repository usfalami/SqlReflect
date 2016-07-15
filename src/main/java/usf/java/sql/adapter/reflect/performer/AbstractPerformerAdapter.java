package usf.java.sql.adapter.reflect.performer;

import java.io.Serializable;
import java.sql.SQLException;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.performer.ExecutorPerformer;
import usf.java.sql.core.reflect.performer.Performer.PerformerAdapter;

public abstract class AbstractPerformerAdapter implements PerformerAdapter {
	
	protected SqlParser sqlParser;
	
	public AbstractPerformerAdapter(SqlParser sqlParser) {
		this.sqlParser = sqlParser;
	}
	
	public void execute(ConnectionManager cm, String query, Serializable... parameters) throws SQLException, AdapterException { //one preparedStatement
		Query sql = sqlParser.parseSQL(query);
		if(sql != null)
			new ExecutorPerformer(cm).run(this, sql, parameters);
	}
//	
//	public void execute(ConnectionManager cm, String... queries) throws SQLException, AdapterException { // only statments
//		if(queries == null) return;
//		ExecutorPerformer e = new ExecutorPerformer(cm);
//		for(String query : queries)
//			e.run(this, sqlParser.parseSQL(query));
//	}
//	public void execute(ConnectionManager cm, String[] queries, Serializable[][] parameters) throws SQLException, AdapterException {
//		if(queries == null || parameters==null || queries.length != parameters.length) return; //throw exception
//		for(int i=0; i<queries.length; i++)
//			this.execute(cm, queries[i], parameters[i]);
//	}

}
