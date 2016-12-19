package usf.java.sqlreflect.reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.mapper.HeaderMapper;
import usf.java.sqlreflect.mapper.filter.MapperFilter;
import usf.java.sqlreflect.sql.entry.Header;

public class SqlUtils {

	public static final List<Header> allColumnNames(ResultSet rs) throws SQLException  {
		try {
			List<Header> list = new ArrayList<Header>();
			ResultSetMetaData md = rs.getMetaData();
			HeaderMapper mapper = new HeaderMapper();
			int cols = md.getColumnCount();
			for(int i=0; i<cols; i++){
				Header header = mapper.map(rs, i+1);
				list.add(header);
			}
			return list;
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}

	public static final List<Header> columnNames(ResultSet rs, String[] columnNames) throws SQLException {
		try {
			List<Header> list = new ArrayList<Header>();
			ResultSetMetaData md = rs.getMetaData();
			HeaderMapper mapper = new HeaderMapper();
			int cols = md.getColumnCount();
			for(int i=0; i<cols; i++){
				String columnName = md.getColumnName(i);
				if(Utils.arraySearch(columnName, columnNames) > -1) {
					Header header = mapper.map(rs, i);
					list.add(header);
				}
			}
			return list;
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}
	
	public static final List<Header> allColumnNames(ResultSet rs, Map<String, MapperFilter> filters) throws SQLException  {
		try {
			List<Header> list = new ArrayList<Header>();
			ResultSetMetaData md = rs.getMetaData();
			HeaderMapper mapper = new HeaderMapper();
			int cols = md.getColumnCount();
			for(int i=1; i<=cols; i++){
				Header header = mapper.map(rs, i);
				list.add(header);
				String columnName = header.getName();
				filters.put(columnName, new MapperFilter(columnName));
			}
			return list;
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}

	
	public static final List<Header> columnNames(ResultSet rs, Map<String, MapperFilter> filters) throws SQLException  {
		try {
			List<Header> list = new ArrayList<Header>();
			ResultSetMetaData md = rs.getMetaData();
			HeaderMapper mapper = new HeaderMapper();
			int cols = md.getColumnCount();
			for(int i=1; i<=cols; i++){
				String columnName = md.getColumnName(i);
				MapperFilter filter = filters.get(columnName);
				if(Utils.isNotNull(filter)){
					Header header = mapper.map(rs, i);
					header.setName(filter.getPropertyName());
					if(!filter.getValueConverter().equals(MapperFilter.DEFAULT_VALUE_CONVERTER)){
						Class<?> clazz = Utils.methodeType(filter.getValueConverter().getClass());
						header.setClassName(clazz.getName());
					}
					list.add(header);
				}
			}
			return list;
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}
}
