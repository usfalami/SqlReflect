package usf.java.sql.reflect.parser;

import java.sql.SQLException;

import usf.java.sql.reflect.Reflector;
import usf.java.sql.reflect.parser.adapter.AbstractParserAdapter;

public interface Parser<T extends AbstractParserAdapter> extends Reflector<T> {

	public abstract void run(T adapter) throws SQLException ;

	public abstract void run(T adapter, String pattern) throws SQLException ;
		
}
