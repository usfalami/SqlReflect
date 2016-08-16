package usf.java.sqlreflect.reflect.performer;

import java.sql.SQLException;

import usf.java.sqlreflect.adapter.PerformAdapter;
import usf.java.sqlreflect.exception.AdapterException;
import usf.java.sqlreflect.reflect.Reflector;

public interface Performer extends Reflector<PerformAdapter> {

	TimePerform run() throws SQLException, AdapterException;
	
}
