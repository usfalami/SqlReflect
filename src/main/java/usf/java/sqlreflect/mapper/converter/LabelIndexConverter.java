package usf.java.sqlreflect.mapper.converter;

public class LabelIndexConverter<T extends Enum<T>> implements Converter<String> {
	
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
