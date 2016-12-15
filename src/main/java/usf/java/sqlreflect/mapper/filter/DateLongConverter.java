package usf.java.sqlreflect.mapper.filter;

import java.util.Date;

public class DateLongConverter implements ValueConverter<Long> {

	@Override
	public Long convert(Object obj) {
		Date date = (Date)obj;
		return date.getTime();
	}

}
