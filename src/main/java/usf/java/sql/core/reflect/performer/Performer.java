package usf.java.sql.core.reflect.performer;

import java.sql.SQLException;

import usf.java.sql.core.adapter.PerformAdapter;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Reflector;

public interface Performer extends Reflector<PerformAdapter> {

	TimePerform run() throws SQLException, AdapterException;
	
}
