package usf.java.sql.core.field;

import java.util.Objects;

import usf.java.sql.core.field.types.ParameterRoles;

public class Column extends Header {

	protected ParameterRoles role;
	
	protected Column() {
		super();
	}
	
	public Column(String name, String valueType, int size, int type) {
		super(name, valueType, size, null);
		this.role = ParameterRoles.values()[type];
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
		Column c = (Column)arg0;
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
