package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.builder.Builder;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class GenericMapper<T> implements Mapper<T> {

	private Class<T> mappedClass;
	private Builder<? super T> builder;
	private Map<String, Metadata> metadataMap;
	
	private Collection<Metadata> metadataList;

	public GenericMapper(Class<T> mappedClassName, Builder<? super T> mapperHandler, String... selectedColumnNames) {
		this.mappedClass = mappedClassName;
		this.builder = mapperHandler;
		this.metadataMap = new HashMap<String, Metadata>();
		if(!Utils.isEmptyArray(selectedColumnNames)){
			for(String columnName : selectedColumnNames)
				metadataMap.put(columnName, new Metadata(columnName));
		}
	}

	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws Exception {
		if(Utils.isEmptyMap(metadataMap))
			fillAllColumns(rs);
		else
			fillSelectedColumns(rs);
		return metadataList;
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T object = mappedClass.newInstance();
		for(Metadata metadata : metadataList) {
			Object value = metadata.get(rs);
			builder.setProperty(object, metadata.getPropertyName(), value);
		}
		return object;
	}
	
	@Override
	public Class<T> getMappedClass() {
		return mappedClass;
	}

	public void addFilter(Metadata metadata) {
		metadataMap.put(metadata.getColumnName(), metadata);
	}
	
	private void fillAllColumns(ResultSet rs) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		metadataList = new ArrayList<Metadata>(cols);
		for(int i=1; i<=cols; i++){
			Metadata mt = Metadata.get(md, i);
			builder.prepareProperty(mappedClass, mt);
			metadataList.add(mt);
		}
	}
	private void fillSelectedColumns(ResultSet rs) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		metadataList = new ArrayList<Metadata>(cols);
		for(int i=1; i<=cols; i++){
			String columnName = md.getColumnName(i);
			Metadata mt = metadataMap.get(columnName);
			if(Utils.isNotNull(mt)) {
				mt.setColumnClassName(md.getColumnClassName(i));
				builder.prepareProperty(mappedClass, mt);
				metadataList.add(mt);
			}
		}
	}
	
}
