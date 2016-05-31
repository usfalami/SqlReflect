package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.adapter.Adapter;
import usf.java.field.SQL;

public interface ExecutorAdapter extends Adapter {
	
	void beforeExec(SQL sql) throws SQLException ;
	void afterExec(SQL sql, ResultSet rs) throws SQLException ;

}
