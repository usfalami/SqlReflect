package usf.java.sqlreflect.mapper.tmp;

public class IndexEnumTransformer<T extends Enum<T>> implements ValueConverter<String> {
	
	private Class<T> clazz;
	
	public IndexEnumTransformer(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public String transformer(Object obj) {
		if(obj == null) return null;
		Integer index = (Integer) obj;
		return clazz.getEnumConstants()[index].toString();
	}

}
