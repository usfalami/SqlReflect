package usf.java.sqlreflect.reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.Filter;
import usf.java.sqlreflect.mapper.entry.HeaderMapper;
import usf.java.sqlreflect.mapper.filter.ResultConverter;
import usf.java.sqlreflect.sql.entry.Header;

public class SqlUtils {

	public static final List<Header> allColumnFilters(ResultSet rs) throws SQLException  {
		ResultSetMetaData md = rs.getMetaData();
		HeaderMapper mapper = new HeaderMapper();
		int cols = md.getColumnCount();
		List<Header> headers = new ArrayList<Header>(cols);
		for(int i=1; i<=cols; i++){
			String columnName = md.getColumnName(i);
			Header header = mapper.map(rs, i);
			header.set(SqlConstants.COLUMN_FILTER, new Filter(columnName));
			header.setPropertyName(columnName);
			headers.add(header);
		}
		return headers;
	}
	
	public static final List<Header> columnFilters(ResultSet rs, Map<String, Filter> filters) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		HeaderMapper mapper = new HeaderMapper();
		int cols = md.getColumnCount();
		List<Header> headers = new ArrayList<Header>(filters.size());
		for(int i=1; i<=cols; i++){
			String columnName = md.getColumnName(i);
			Filter filter = filters.get(columnName);
			if(Utils.isNotNull(filter)){
				Header header = mapper.map(rs, i);
				header.setPropertyName(filter.getPropertyName());
				header.set(SqlConstants.COLUMN_FILTER, filter);
				ResultConverter<?> conv = filter.getConverter();
				if(!conv.equals(Filter.DEFAULT_VALUE_CONVERTER)){
					try {
						String className = Utils.methodeType(conv.getClass()).getName();
						header.setColumnClassName(className);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				headers.add(header);
			}
		}
		return headers;
	}
}
