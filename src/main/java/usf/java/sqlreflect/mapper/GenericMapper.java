package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.builder.Builder;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class GenericMapper<T> implements Mapper<T> {

	private Class<T> mappedClassName;
	private Map<String, Metadata> metadataMap;
	private Builder<? super T> builder;
	
	private Collection<Metadata> metadataList;

	public GenericMapper(Class<T> mappedClassName, Builder<? super T> mapperHandler, String... selectedColumnNames) {
		this.mappedClassName = mappedClassName;
		this.builder = mapperHandler;
		this.metadataMap = new HashMap<String, Metadata>();
		if(!Utils.isEmptyArray(selectedColumnNames)){
			for(String columnName : selectedColumnNames)
				metadataMap.put(columnName, new Metadata(columnName));
		}
	}

	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		metadataList = Utils.isEmptyMap(metadataMap) ? fillAllColumns(rs) : fillselectedColumns(rs);
		builder.prepare(metadataList);
		return metadataList;
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T object = mappedClassName.newInstance();
		for(Metadata metadata : metadataList) {
			Object value = metadata.get(rs);
			builder.setProperty(object, metadata.getPropertyName(), value);
		}
		return object;
	}

	public void addFilter(Metadata metadata) {
		metadataMap.put(metadata.getColumnName(), metadata);
	}
	
	private Collection<Metadata> fillAllColumns(ResultSet rs) throws SQLException  {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		for(int i=1; i<=cols; i++){
			Metadata mt = Metadata.get(md, i);
			metadataMap.put(mt.getColumnName(), mt);
		}
		return metadataMap.values();
	}
	private Collection<Metadata> fillselectedColumns(ResultSet rs) throws SQLException  {
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
