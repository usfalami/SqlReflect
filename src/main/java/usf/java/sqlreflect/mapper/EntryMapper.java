package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.stream.StreamWriter;

public class EntryMapper<T extends Entry> implements Mapper<T> {

	private Class<T> clazz;
	private String[] columnNames;
	
	public EntryMapper(Class<T> clazz, String... columnNames) {
		this.clazz = clazz;
		this.columnNames = columnNames;
	}

	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		if(Utils.isEmptyArray(columnNames)) // set all column if no column was set
			columnNames = Utils.columnNames(rs);
	}
	
	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = clazz.newInstance();
		for(String column : columnNames)
			item.set(column, rs.getObject(column));
		return item;
	}

	@Override
	public String[] getSelectedColumns() {
		return columnNames;
	}
	@Override
	public void write(StreamWriter writer, T bean) throws Exception {
		writer.startObject("Entry");
		String columns[] = getSelectedColumns();
		for(String column : columns)
			writer.writeString(column, bean.get(column)+"");
		writer.endObject();
	}

	public Class<T> getClazz() {
		return clazz;
	}
	
}
