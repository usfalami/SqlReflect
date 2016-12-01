package usf.java.sqlreflect.mapper.tmp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.stream.StreamWriter;

public class EntryMapper<T extends Entry> implements Mapper<T> {

	private Map<String, ColumnTransformer> columnTransformers;
	
	private Class<T> clazz;
	private String[] columns;
	
	public EntryMapper(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void prepare(ResultSet rs) throws SQLException {
		if(Utils.isEmptyArray(columns)) // set all column if no column was set
			this.columns = Utils.columnNames(rs);
		if(columnTransformers == null) columnTransformers = new HashMap<String, ColumnTransformer>();
		for(String column : columns){
			if(columnTransformers.get(column) == null)
				columnTransformers.put(column, new ColumnTransformer(column, column));
		}
	}
	
	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = clazz.newInstance();
		for(java.util.Map.Entry<String, ColumnTransformer> c : columnTransformers.entrySet()) {
			ColumnTransformer trans = c.getValue();
			Object value = rs.getObject(c.getKey());
			item.set(trans.getColumnName(), trans.getValueConverter().transformer(value));
		}
		return item;
	}
	@Override
	public void write(StreamWriter writer, T bean) throws Exception {
		writer.startObject("bean");
		for(String column : getColumnNames())
			writer.writeString(column, bean.get(column)+"");
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return columns;
	}
	
	
}
