package usf.java.sqlreflect.mapper.filter;

public interface ValueConverter<T> {
	
	T convert(Object obj);

}
