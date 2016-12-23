package usf.java.sqlreflect.mapper.filter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Metadata {
	
	private String columnName, propertyName, columnClassName;
	
	public Metadata(String columnName) {
		this(columnName, columnName);
	}
	public Metadata(String columnName, String propertyName) {
		this.columnName = columnName;
		this.propertyName = propertyName;
	}

	public String getColumnName() {
		return columnName;
	}
	public Metadata setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}

	public String getPropertyName() {
		return propertyName;
	}
	public Metadata setPropertyName(String propertyName) {
		this.propertyName = propertyName;
		return this;
	}

	public String getColumnClassName() {
		return columnClassName;
	}
	public Metadata setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
		return this;
	}

	public static Metadata get(ResultSetMetaData md, int index) throws SQLException{
		Metadata metadata = new Metadata(md.getColumnName(index));
		metadata.setColumnClassName(md.getColumnClassName(index));
		return metadata;
	}
	
	public Object get(ResultSet rs) throws SQLException{
		return rs.getObject(getColumnName());
	}
	
}
