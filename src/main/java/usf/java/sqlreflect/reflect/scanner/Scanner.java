package usf.java.sqlreflect.reflect.scanner;

import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.exception.AdapterException;
import usf.java.sqlreflect.reflect.Reflector;

public interface Scanner<T> extends Reflector<ScannerAdapter<T>> {
	
	List<T> run() throws SQLException, AdapterException;
	
}
