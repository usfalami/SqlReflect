package usf.java.sqlreflect.mapper.tmp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import usf.java.sqlreflect.sql.entry.Entry;

public class AdvancedEntryMapper<T extends Entry> extends EntryMapper<T> {

	private Map<String, MapperFilter> mapperFilters;

	public AdvancedEntryMapper(Class<T> clazz, String... columnNames) {
		super(clazz, columnNames);
		mapperFilters = new HashMap<String, MapperFilter>();
	}

	@Override
	public void prepare(ResultSet rs) throws SQLException {
		super.prepare(rs);
		for(String column : super.getColumnNames()){
			if(mapperFilters.get(column) == null)
				mapperFilters.put(column, new MapperFilter(column, column));
		}
	}
	
	public T map(ResultSet rs, int row) throws Exception {
		T item = getClazz().newInstance();
		for(java.util.Map.Entry<String, MapperFilter> c : mapperFilters.entrySet()) {
			MapperFilter trans = c.getValue();
			Object value = rs.getObject(c.getKey());
			item.set(trans.getMappedName(), trans.getValueConverter().transformer(value));
		}
		return item;
	}
		
	public void addMapperFilter(String columnName, String mappedName, ValueConverter<?> converter) {
		if(columnName == null) return;
		mappedName = mappedName == null ? columnName : mappedName;
		converter = converter == null ? new DefaultTransformer() : converter;
		mapperFilters.put(columnName, new MapperFilter(columnName, mappedName, converter));
	}
	
	@Override
	public String[] getColumnNames() {
		String[] mappedColumn = new String[mapperFilters.size()];
		int i=0;
		for(Iterator<MapperFilter> c = mapperFilters.values().iterator(); c.hasNext(); i++)
			mappedColumn[i] = c.next().getMappedName();
		return mappedColumn;
	}
	
}
