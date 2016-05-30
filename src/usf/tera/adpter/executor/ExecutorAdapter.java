package usf.tera.adpter.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.tera.adpter.Adapter;
import usf.tera.field.SQL;

public interface ExecutorAdapter extends Adapter {
	
	void beforeExec(SQL sql) throws SQLException ;
	void afterExec(SQL sql, ResultSet rs) throws SQLException ;

}
