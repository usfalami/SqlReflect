package usf.java.reflect.parser;

import java.sql.SQLException;

import usf.java.reflect.Reflector;
import usf.java.reflect.parser.adapter.ParserAdapter;

public interface Parser extends Reflector<ParserAdapter> {

	public abstract void run(ParserAdapter adapter) throws SQLException ;

	public abstract void run(ParserAdapter adapter, String pattern) throws SQLException ;
	
}
