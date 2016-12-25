package usf.java.sqlreflect.mapper.builder;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.Utils;

public class ObjectReflectBuild implements Builder<Object> {
	
	private Map<String, Method> methodMap;
	
	public ObjectReflectBuild() {
		methodMap = new HashMap<String, Method>();
	}

	@Override
	public <D extends Object> void prepareProperty(Class<D> derivedClass, Metadata metadata) throws SQLException {
		try {
			String name = metadata.getPropertyName();
			Method method = derivedClass.getMethod(
					Utils.setterOf(name), Class.forName(metadata.getColumnClassName()));
			methodMap.put(name, method);
		} catch (Exception  e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setProperty(Object obj, String propertyName, Object value) throws Exception {
		methodMap.get(propertyName).invoke(obj, value);
	}
	

}
