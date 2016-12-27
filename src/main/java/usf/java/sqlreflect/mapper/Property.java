package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Property {
	
	private String columnName, name, className;
	
	public Property(String columnName) {
		this(columnName, columnName);
	}
	public Property(String columnName, String propertyName) {
		this.columnName = columnName;
		this.name = propertyName;
	}

	public String getColumnName() {
		return columnName;
	}
	public Property setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}

	public String getName() {
		return name;
	}
	public Property setName(String name) {
		this.name = name;
		return this;
	}

	public String getClassName() {
		return className;
	}
	public Property setClassName(String className) {
		this.className = className;
		return this;
	}

	public static Property get(ResultSetMetaData md, int index) throws SQLException {
		Property property = new Property(md.getColumnName(index));
		property.setClassName(md.getColumnClassName(index));
		return property;
	}
	
	public Object get(ResultSet rs) throws SQLException{
		return rs.getObject(getColumnName());
	}
	
}
