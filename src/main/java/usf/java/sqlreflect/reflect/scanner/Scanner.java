package usf.java.sqlreflect.reflect.scanner;

import java.util.List;

import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.reflect.Reflector;

public interface Scanner<T> extends Reflector<ScannerAdapter<T>> {
	
	List<T> run() throws Exception;
	
}
