package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.filter.HasFilters;
import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.mapper.filter.MetadataConverter;
import usf.java.sqlreflect.mapper.filter.ResultConverter;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class FiltredMapper<T> implements Mapper<T>, HasFilters {
	
	private Class<T> mappedClass;
	private Map<String, Metadata> metadataMap;
	private PropertyMapper<T> propertyMapper;
	
	private Collection<Metadata> metadataList;

	public FiltredMapper(Class<T> clazz, PropertyMapper<T> propertyMapper, String... selectedColumnNames) {
		this.mappedClass = clazz;
		this.propertyMapper = propertyMapper;
		this.metadataMap = new HashMap<String, Metadata>();
		if(!Utils.isEmptyArray(selectedColumnNames)){
			for(String columnName : selectedColumnNames)
				metadataMap.put(columnName, new Metadata(columnName));
		}
	}

	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		metadataList = Utils.isEmptyMap(metadataMap) ? selectAllColumns(rs) : selectColumns(rs);
		propertyMapper.prepare(metadataList);
		return metadataList;
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T object = mappedClass.newInstance();
		for(Metadata metadata : metadataList) {
			Object value = metadata.get(rs);
			propertyMapper.setProperty(object, metadata.getPropertyName(), value);
		}
		return object;
	}

	@Override
	public void addFilter(String columnName, String propertyName) {
		metadataMap.put(columnName, new Metadata(columnName, propertyName));
	}
	@Override
	public void addFilter(String columnName, ResultConverter<?> converter) {
		metadataMap.put(columnName, new MetadataConverter(columnName, converter));
	}
	@Override
	public void addFilter(String columnName, String propertyName, ResultConverter<?> converter) {
		metadataMap.put(columnName, new MetadataConverter(columnName, propertyName, converter));
	}
	
	@Override
	public Class<T> getMappedClass() {
		return mappedClass;
	}
	
	protected Collection<Metadata> selectAllColumns(ResultSet rs) throws SQLException  {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		for(int i=1; i<=cols; i++){
			Metadata mt = Metadata.get(md, i);
			metadataMap.put(mt.getColumnName(), mt);
		}
		return metadataMap.values();
	}
	protected Collection<Metadata> selectColumns(ResultSet rs) throws SQLException  {
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
