package usf.java.sql.core.field;

import java.util.Objects;

public class Parameter implements Field {

	protected String name, type, clazz;
	protected ParameterRoles role;
	protected int size;
	
	protected Parameter() {
		super();
	}
	
	public Parameter(String name, String valueType, int size, int type) {
		this.name = name;
		this.type = valueType;
		this.size = size;
		this.role = ParameterRoles.values()[type];
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValueType() {
		return type;
	}
	public void setValueType(String type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public ParameterRoles getRole() {
		return role;
	}
	public void setRole(ParameterRoles role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null) return false; 
		if(!arg0.getClass().equals(this.getClass())) return false;
		Parameter c = (Parameter)arg0;
		return Objects.equals(name, c.name) && 
				Objects.equals(type, c.type) &&
				Objects.equals(role, c.role) && 
				Objects.equals(size, c.size); 
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", clazz=" + clazz
				+ ", role=" + role + ", size=" + size + "]";
	}

}
