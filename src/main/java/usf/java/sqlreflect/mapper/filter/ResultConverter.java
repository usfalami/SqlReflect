package usf.java.sqlreflect.mapper.filter;

public interface ResultConverter<R> {
	
	R convert(Object obj);

}
