package usf.java.sqlreflect.field;

public class Parameter<T> {
	
	private String name;//TODO later, namedQuery
	private T value; //TODO see getObject(int columnIndex, Class<T> type)
	private boolean out;
	
	public Parameter(T value) {
		this(value, false, null);
	}
	public Parameter(T value, boolean out) {
		this(value, out, null);
	}
	public Parameter(T value, boolean out, String name) {
		this.value = value;
		this.out = out;
		this.name = name;
	}
	
	public T getValue() {
		return value;
	}
	public Parameter<T> setValue(T value) {
		this.value = value;
		return this;
	}
	public boolean isOut() {
		return out;
	}
	public Parameter<T> setOut(boolean out) {
		this.out = out;
		return this;
	}
	public String getName() {
		return name;
	}
	public Parameter<T> setName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public String toString() {
		return "Parameter [name=" + name + ", value=" + value + ", out=" + out + "]";
	}

}
