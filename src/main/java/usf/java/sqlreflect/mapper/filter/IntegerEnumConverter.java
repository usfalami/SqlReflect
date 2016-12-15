package usf.java.sqlreflect.mapper.filter;

public class IntegerEnumConverter<T extends Enum<T>> implements ValueConverter<String> {
	
	private Class<T> clazz;
	
	public IntegerEnumConverter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public String convert(Object obj) {
		Integer index = (Integer) obj;
		return clazz.getEnumConstants()[index].toString();
	}

}
