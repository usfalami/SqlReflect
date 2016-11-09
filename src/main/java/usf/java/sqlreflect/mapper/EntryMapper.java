package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.stream.StreamWriter;

public class EntryMapper<T extends Entry> implements Mapper<T> {

	private Map<String, String> columnMapper;
	private Class<T> clazz;
	
	public EntryMapper(Class<T> clazz) {
		this(clazz, new HashMap<String, String>());
	}
	public EntryMapper(Class<T> clazz, Map<String, String> columnMapper) {
		this.clazz = clazz;
		setColumnMapper(columnMapper);
	}
	public EntryMapper(Class<T> clazz, String... columnNames) {
		this.clazz = clazz;
		setColumnNames(columnNames);
	}

	@Override
	public void prepare(ResultSet rs) throws SQLException {
		if(Utils.isEmptyMap(columnMapper)) // set all column if no column was set
			setColumnNames(Utils.columnNames(rs));
	}
	
	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = clazz.newInstance();
		for(java.util.Map.Entry<String, String> c : columnMapper.entrySet())
			item.set(c.getValue(), rs.getObject(c.getKey()));
		return item;
	}
	@Override
	public void write(StreamWriter writer, T bean) throws Exception {
		writer.startObject("Entry");
		for(String str : columnMapper.values())
			writer.writeString(str, "" + bean.get(str));
		writer.endObject();
	}

	@Override
	public String[] getColumnNames() {
		Collection<String> set = columnMapper.values();
		return set.toArray(new String[set.size()]);
	}
	
	public void setColumnNames(String... columnNames) {
		columnMapper.clear();
		for(String cn : columnNames)
			columnMapper.put(cn, cn);
	}
	/**
	 * KEY	 	: SQL column name <br>
	 * VALUE 	: bean field name
	 */
	public void setColumnMapper(Map<String, String> columnMapper) {
		this.columnMapper = columnMapper;
	}
	
}
