package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.mapper.builder.Builder;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class DefaultMapper<T> implements Mapper<T> {

	private Class<T> mappedClass;
	private Builder builder;
	private Map<String, Property> propertiesMap;
	
	private Collection<Property> propertiesList;

	public DefaultMapper(Class<T> mappedClassName, Builder mapperHandler, String... selectedColumnNames) {
		this.mappedClass = mappedClassName;
		this.builder = mapperHandler;
		this.propertiesMap = new HashMap<String, Property>();
		if(!Utils.isEmptyArray(selectedColumnNames)){
			for(String columnName : selectedColumnNames)
				propertiesMap.put(columnName, new Property(columnName));
		}
	}

	@Override
	public Collection<Property> prepare(ResultSet rs, DatabaseType type) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		if(Utils.isEmptyMap(propertiesMap))
			fillAllColumns(md);
		else
			fillSelectedColumns(md);
		return propertiesList;
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T object = mappedClass.newInstance();
		for(Property property : propertiesList) {
			Object value = property.get(rs);
			builder.set(object, property, value);
		}
		return object;
	}
	
	@Override
	public Class<T> getMappedClass() {
		return mappedClass;
	}

	public void addPropertyFilter(Property property) {
		propertiesMap.put(property.getColumnName(), property);
	}
	
	
	private void fillAllColumns(ResultSetMetaData rm) throws Exception {
		int cols = rm.getColumnCount();
		propertiesList = new ArrayList<Property>(cols);
		for(int i=1; i<=cols; i++){
			Property mt = Property.get(rm, i);
			builder.prepare(mappedClass, mt);
			propertiesList.add(mt);
		}
	}
	private void fillSelectedColumns(ResultSetMetaData rm) throws Exception {
		int cols = rm.getColumnCount();
		propertiesList = new ArrayList<Property>(cols);
		for(int i=1; i<=cols; i++){
			String columnName = rm.getColumnName(i);
			Property mt = propertiesMap.get(columnName);
			if(Utils.isNotNull(mt)) {
				//if className isEmpty
				mt.setClassName(rm.getColumnClassName(i));
				builder.prepare(mappedClass, mt);
				propertiesList.add(mt);
			}
		}
	}
	
}
