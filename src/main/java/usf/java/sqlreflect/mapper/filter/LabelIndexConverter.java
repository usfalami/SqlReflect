package usf.java.sqlreflect.mapper.filter;

public class LabelIndexConverter<T extends Enum<T>> implements ResultConverter<String> {
	
	private Class<T> clazz;
	
	public LabelIndexConverter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public String convert(Object obj) {
		Integer index = (Integer) obj;
		return clazz.getEnumConstants()[index].toString();
	}

}
