package usf.java.sqlreflect.field;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

public class ParameterFactory {
	
	//BIT wrapper
	public static final Parameter<Boolean> BIT_WRAPPER(boolean value){
		return new Parameter<Boolean>(Types.BIT, value);
	}
	//SMALLINT mapper
	public static final Parameter<Short> SMALLINT_WRAPPER(short value){
		return new Parameter<Short>(Types.SMALLINT, value);  
	}
	//CHAR wrapper
	public static final Parameter<Character> CHAR_WRAPPER(int value){
		return new Parameter<Character>(Types.CHAR, (char)value);  
	}
	public static final Parameter<Character> CHAR_WRAPPER(char value){
		return new Parameter<Character>(Types.CHAR, value);  
	}
	// INTEGER wrapper
	public static final Parameter<Integer> INTEGER_WRAPPER(int value){
		return new Parameter<Integer>(Types.INTEGER, new Integer(value));  
	}
	// BIGINT wrapper
	public static final Parameter<Long> BIGINT_WRAPPER(long value){
		return new Parameter<Long>(Types.BIGINT, new Long(value));  
	}
	// REAL wrapper
	public static final Parameter<Float> REAL_WRAPPER(float value){
		return new Parameter<Float>(Types.FLOAT, new Float(value));  
	}
	// DOUBLE wrapper
	public static final Parameter<Double> DOUBLE_WRAPPER(double value){
		return new Parameter<Double>(Types.DOUBLE, new Double(value));  
	}
	// NUMERIC wrapper
	public static final Parameter<BigInteger> NUMERIC_WRAPPER(BigInteger value){
		return new Parameter<BigInteger>(Types.NUMERIC, value);  
	}
	// DECIMAL wrapper
	public static final Parameter<BigDecimal> DECIMAL_WRAPPER(BigDecimal value){
		return new Parameter<BigDecimal>(Types.DECIMAL, value);  
	}	
	// VARCHAR wrapper
	public static final Parameter<String> VARCHAR_WRAPPER(Object obj){
		return new Parameter<String>(Types.VARCHAR, obj == null ? null : obj.toString());  
	}
	// LONGVARCHAR wrapper
	public static final Parameter<String> LONGVARCHAR_WRAPPER(Object obj){
		return new Parameter<String>(Types.LONGVARCHAR, obj == null ? null : obj.toString());  
	}
	// DATE wrapper
	public static final Parameter<Date> DATE_WRAPPER(long value){
		return new Parameter<Date>(Types.DATE, new Date(value));  
	}
	public static final Parameter<Date> DATE_WRAPPER(java.util.Date value){
		return new Parameter<Date>(Types.DATE, value == null ? null : new Date(value.getTime()));  
	}
	// TIMESTAMP wrapper
	public static final Parameter<Timestamp> TIMESTAMP_WRAPPER(long value){
		return new Parameter<Timestamp>(Types.TIMESTAMP, new Timestamp(value));  
	}
	public static final Parameter<Timestamp> TIMESTAMP_WRAPPER(java.util.Date value){
		return new Parameter<Timestamp>(Types.TIMESTAMP, value == null ? null : new Timestamp(value.getTime()));  
	}
	// TIME wrapper
	public static final Parameter<Time> TIME_WRAPPER(long value){
		return new Parameter<Time>(Types.TIME, new Time(value));
	}
	public static final Parameter<Time> TIME_WRAPPER(java.util.Date value){
		return new Parameter<Time>(Types.TIME, value == null ? null : new Time(value.getTime()));  
	}
	
}
