package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import usf.java.sqlreflect.reflect.SqlUtils;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class EntryMapper<T extends Entry> implements Mapper<T> {

	private Class<T> mappedClass;
	private List<Header> headers;
	private String[] selectedColumnNames;

	public EntryMapper(Class<T> clazz, String... selectedColumnNames) {
		this.mappedClass = clazz;
		this.selectedColumnNames = selectedColumnNames;
	}

	@Override
	public Collection<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {//return template<R>
		headers = Utils.isEmptyArray(selectedColumnNames) ? 
				SqlUtils.allColumnNames(rs) : SqlUtils.columnNames(rs, selectedColumnNames);
		return headers;
	}
	
	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = mappedClass.newInstance();
		for(Header header : headers){
			String columnName = header.getName();
			item.set(columnName, rs.getObject(columnName));
		}
		return item;
	}
	
}
