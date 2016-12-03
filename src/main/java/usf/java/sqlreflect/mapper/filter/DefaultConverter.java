package usf.java.sqlreflect.mapper.filter;

public class DefaultConverter implements ValueConverter<Object> {
	
	@Override
	public Object transformer(Object obj) {
		return obj;
	}

}
