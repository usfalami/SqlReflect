package usf.java.sql.adapter.reflect;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.parser.SqlParser;

public abstract class AbstractReflectorAdapter {

	protected Formatter formatter;
	protected SqlParser sqlParser;
	
	public AbstractReflectorAdapter(SqlParser sqlParser, Formatter formatter) {
		this.formatter = formatter;
		this.sqlParser = sqlParser;
	}

	
	public static final DateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	public static final DateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss.SSS");
	//Custom parameters

	public static final int DATABASE_NAME_LENGTH 		= -50;
	
	public static final int COLUMN_NUM_LENGTH    		= -2;
	public static final int COLUMN_NAME_LENGTH   		= -30;
	public static final int COLUMN_VALUE_TYPE_LENGTH	= -10;
	public static final int COLUMN_SIZE_LENGTH   	   	= -8;
	public static final int COLUMN_CLASS_LENGTH  		= -30;
	public static final int COLUMN_TYPE_LENGTH   		= -10;
	public static final int COLUMN_PARAM_LENGTH 		= -60;
	
	public static final int PERFORM_TEXT_LENGTH 		= -15;
	
	public static final int VALUE_LENGTH 				= -21;

}
