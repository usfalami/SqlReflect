package usf.java.sqlreflect.reflect;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import usf.java.sqlreflect.mapper.filter.Metadata;

public class SqlUtils {
	
	public static final Collection<Metadata> allColumn(ResultSet rs, Map<String, Metadata> metadataMap) throws SQLException  {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		for(int i=1; i<=cols; i++){
			Metadata mt = Metadata.get(md, i);
			metadataMap.put(mt.getColumnName(), mt);
		}
		return metadataMap.values();
	}
	
	public static final Collection<Metadata> columns(ResultSet rs, Map<String, Metadata> metadataMap) throws SQLException  {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		for(int i=1; i<=cols; i++){
			String columnName = md.getColumnName(i);
			Metadata mt = metadataMap.get(columnName);
			if(Utils.isNotNull(mt))
				mt.setColumnClassName(md.getColumnClassName(i));
		}
		return metadataMap.values();
	}

}
