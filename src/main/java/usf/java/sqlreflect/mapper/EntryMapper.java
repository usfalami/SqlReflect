package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.writer.TypeWriter;

public class EntryMapper<T extends Entry> implements Mapper<T> {

	private Class<T> mappedClass;
	private String[] columnNames;

	private ResultSet resultSet;
	
	public EntryMapper(Class<T> clazz, String... columnNames) {
		this.mappedClass = clazz;
		this.columnNames = columnNames;
	}

	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {//return template<R>
		this.resultSet = rs;
		if(Utils.isEmptyArray(columnNames)) // set all column if no column was set
			columnNames = Utils.columnNames(rs);
	}
	
	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = mappedClass.newInstance();
		for(String column : columnNames)
			item.set(column, rs.getObject(column));
		return item;
	}

	@Override
	public Map<String, TypeWriter> getTypes() throws SQLException {
		return Utils.columnTypes(resultSet.getMetaData(), getSelectedColumns());
	}

	public String[] getSelectedColumns() {
		return columnNames;
	}
	
	public Class<T> getMappedClass() {
		return mappedClass;
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	
}
