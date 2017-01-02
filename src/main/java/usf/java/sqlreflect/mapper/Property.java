package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Property {
	
	private String columnName, name, className;
	private Map<String, Object> map;
	
	public Property(String columnName) {
		this(columnName, columnName);
	}
	public Property(String name, String columnName) {
		this.name = name;
		this.columnName = columnName;
		this.map = new HashMap<String, Object>();
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
	
	public void setField(String key, Object obj) {
		map.put(key, obj);
	}
	public <T> T getField(String key) {
		return (T) map.get(key);
	}
	
	public Object get(ResultSet rs) throws SQLException{
		return rs.getObject(getColumnName());
	}

	public static Property get(ResultSetMetaData md, int index) throws SQLException {
		Property property = new Property(md.getColumnName(index));
		property.setClassName(md.getColumnClassName(index));
		return property;
	}
}
