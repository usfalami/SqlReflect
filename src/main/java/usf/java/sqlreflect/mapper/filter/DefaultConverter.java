package usf.java.sqlreflect.mapper.filter;

public class DefaultConverter implements ResultConverter<Object> {
	
	@Override
	public Object convert(Object obj) {
		return obj;
	}

}
