package usf.java.sql.adapter.reflect.scanner;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.AbstractAdapter;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.scanner.Scanner.ScannerAdapter;

public abstract class AbstractScannerAdapter<T> extends AbstractAdapter implements ScannerAdapter<T> {

	protected Mapper<T> mapper;
	
	public AbstractScannerAdapter(SqlParser sqlParser, Mapper<T> mapper, Formatter formatter) {
		super(sqlParser, formatter);
		this.mapper = mapper;
	}
	
	public Mapper<T> getMapper() {
		return mapper;
	}

}
