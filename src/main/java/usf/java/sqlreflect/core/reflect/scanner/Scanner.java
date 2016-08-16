package usf.java.sqlreflect.core.reflect.scanner;

import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.core.adapter.ScannerAdapter;
import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.reflect.Reflector;

public interface Scanner<T> extends Reflector<ScannerAdapter<T>> {
	
	List<T> run() throws SQLException, AdapterException;
	
}
