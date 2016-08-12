package usf.java.sql.core.reflect.performer;

import java.sql.SQLException;

import usf.java.sql.core.adapter.PerformAdapter;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.reflect.Worker;

public interface Performer extends Worker<PerformAdapter> {

	TimePerform run() throws SQLException, AdapterException;
	
}
