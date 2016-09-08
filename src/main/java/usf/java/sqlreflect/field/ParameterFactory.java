package usf.java.sqlreflect.field;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class ParameterFactory {
	
	//BIT wrapper
	public static final Parameter<Boolean> BIT_WRAPPER(boolean value){
		return new Parameter<Boolean>(value);  
	}
	//SMALLINT mapper
	public static final Parameter<Short> SMALLINT_WRAPPER(short value){
		return new Parameter<Short>(value);  
	}
	//CHAR wrapper
	public static final Parameter<Character> CHAR_WRAPPER(int value){
		return new Parameter<Character>((char)value);  
	}
	public static final Parameter<Character> CHAR_WRAPPER(char value){
		return new Parameter<Character>(value);  
	}
	// INTEGER wrapper
	public static final Parameter<Integer> INTEGER_WRAPPER(int value){
		return new Parameter<Integer>(new Integer(value));  
	}
	// BIGINT wrapper
	public static final Parameter<Long> BIGINT_WRAPPER(long value){
		return new Parameter<Long>(new Long(value));  
	}
	// REAL wrapper
	public static final Parameter<Float> REAL_WRAPPER(float value){
		return new Parameter<Float>(new Float(value));  
	}
	// DOUBLE wrapper
	public static final Parameter<Double> DOUBLE_WRAPPER(double value){
		return new Parameter<Double>(new Double(value));  
	}
	// NUMERIC wrapper
	public static final Parameter<BigInteger> NUMERIC_WRAPPER(BigInteger value){
		return new Parameter<BigInteger>(value);  
	}
	// DECIMAL wrapper
	public static final Parameter<BigDecimal> DECIMAL_WRAPPER(BigDecimal value){
		return new Parameter<BigDecimal>(value);  
	}	
	// VARCHAR wrapper
	public static final Parameter<String> VARCHAR_WRAPPER(Object obj){
		return new Parameter<String>(obj == null ? null : obj.toString());  
	}
	// LONGVARCHAR wrapper
	public static final Parameter<String> LONGVARCHAR_WRAPPER(Object obj){
		return new Parameter<String>(obj == null ? null : obj.toString());  
	}
	// DATE wrapper
	public static final Parameter<Date> DATE_WRAPPER(long value){
		return new Parameter<Date>(new Date(value));  
	}
	public static final Parameter<Date> DATE_WRAPPER(java.util.Date value){
		return new Parameter<Date>(value == null ? null : new Date(value.getTime()));  
	}
	// TIMESTAMP wrapper
	public static final Parameter<Timestamp> TIMESTAMP_WRAPPER(long value){
		return new Parameter<Timestamp>(new Timestamp(value));  
	}
	public static final Parameter<Timestamp> TIMESTAMP_WRAPPER(java.util.Date value){
		return new Parameter<Timestamp>(value == null ? null : new Timestamp(value.getTime()));  
	}
	// TIME wrapper
	public static final Parameter<Time> TIME_WRAPPER(long value){
		return new Parameter<Time>(new Time(value));
	}
	public static final Parameter<Time> TIME_WRAPPER(java.util.Date value){
		return new Parameter<Time>(value == null ? null : new Time(value.getTime()));  
	}
	
}
