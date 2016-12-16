package usf.java.sqlreflect.mapper.filter;

public interface ResultConverter<T> {
	
	T convert(Object obj);

}
