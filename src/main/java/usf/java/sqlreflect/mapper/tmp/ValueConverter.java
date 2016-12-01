package usf.java.sqlreflect.mapper.tmp;

public interface ValueConverter<T> {
	
	T transformer(Object obj);

}
