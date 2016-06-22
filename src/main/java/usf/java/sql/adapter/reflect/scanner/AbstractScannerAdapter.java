package usf.java.sql.adapter.reflect.scanner;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.AbstractReflectorAdapter;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.scanner.Scanner.HasScanner;

public abstract class AbstractScannerAdapter<T> extends AbstractReflectorAdapter implements HasScanner<T> {

	protected Mapper<T> mapper;
	
	public AbstractScannerAdapter(SqlParser sqlParser, Mapper<T> mapper, Formatter formatter) {
		super(sqlParser, formatter);
		this.mapper = mapper;
	}
	
	public Mapper<T> getMapper() {
		return mapper;
	}

}
