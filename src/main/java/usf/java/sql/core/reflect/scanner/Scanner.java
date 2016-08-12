package usf.java.sql.core.reflect.scanner;

import java.sql.SQLException;
import java.util.List;

import usf.java.sql.core.adapter.ScannerAdapter;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Worker;

public interface Scanner<T> extends Worker<ScannerAdapter<T>> {
	
	List<T> run() throws SQLException, AdapterException;
	

	
}
