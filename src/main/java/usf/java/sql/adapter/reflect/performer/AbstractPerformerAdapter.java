package usf.java.sql.adapter.reflect.performer;

import java.io.Serializable;
import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.AbstractAdapter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.performer.ExecutorPerformer;
import usf.java.sql.core.reflect.performer.Performer.PerformerAdapter;

public abstract class AbstractPerformerAdapter extends AbstractAdapter implements PerformerAdapter {
	
	public AbstractPerformerAdapter(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, formatter);
	}
	
	public void execute(ConnectionManager cm, String query, Serializable... parameters) throws SQLException { //one preparedStatement
		Callable sql = sqlParser.parseSQL(query);
		if(sql != null)
			new ExecutorPerformer(cm).run(this, sql, parameters);
	}
	
	public void execute(ConnectionManager cm, String... queries) throws SQLException { // only statments
		if(queries == null) return;
		ExecutorPerformer e = new ExecutorPerformer(cm);
		for(String query : queries)
			e.run(this, sqlParser.parseSQL(query));
	}
	public void execute(ConnectionManager cm, String[] queries, Serializable[][] parameters) throws SQLException {
		if(queries == null || parameters==null || queries.length != parameters.length) return; //throw exception
		for(int i=0; i<queries.length; i++)
			this.execute(cm, queries[i], parameters[i]);
	}

}
