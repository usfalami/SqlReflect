package usf.java.sql.db.field;

import usf.java.sql.db.ColumnRoles;

public class Column implements Field {

	protected String name, type, clazz;
	protected ColumnRoles role;
	protected int size;
	
	public Column(String name, String valueType, int size, int type) {
		this.name = name;
		this.type = valueType;
		this.size = size;
		this.role = ColumnRoles.values()[type];
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
	
	public ColumnRoles getRole() {
		return role;
	}
	public void setRole(ColumnRoles role) {
		this.role = role;
	}

}
