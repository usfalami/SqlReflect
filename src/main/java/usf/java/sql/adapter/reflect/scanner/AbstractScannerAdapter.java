package usf.java.sql.adapter.reflect.scanner;

import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.scanner.Scanner.ScannerAdapter;

public abstract class AbstractScannerAdapter<T> implements ScannerAdapter<T> {

	protected Mapper<T> mapper;
	
	public AbstractScannerAdapter(Mapper<T> mapper) {
		this.mapper = mapper;
	}
	
	public Mapper<T> getMapper() {
		return mapper;
	}

}
