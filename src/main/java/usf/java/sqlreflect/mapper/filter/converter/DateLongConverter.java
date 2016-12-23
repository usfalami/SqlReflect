package usf.java.sqlreflect.mapper.filter.converter;

import java.util.Date;

public class DateLongConverter implements Converter<Long> {

	@Override
	public Long convert(Object obj) {
		Date date = (Date)obj;
		return date.getTime();
	}

}
