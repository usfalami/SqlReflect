package usf.java.sqlreflect.core.reflect.performer;

import java.sql.SQLException;

import usf.java.sqlreflect.core.adapter.PerformAdapter;
import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.reflect.Reflector;

public interface Performer extends Reflector<PerformAdapter> {

	TimePerform run() throws SQLException, AdapterException;
	
}
