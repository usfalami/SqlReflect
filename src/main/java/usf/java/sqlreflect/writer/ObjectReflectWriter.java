package usf.java.sqlreflect.writer;

import java.lang.reflect.Method;
import java.util.Collection;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.stream.StreamWriter;

public class ObjectReflectWriter implements Writer<Object> {

	private Collection<Property> properties;

	@Override
	public <D extends Object> void prepare(Class<D> derivedClass, Collection<Property> properties) throws Exception {
		this.properties = properties;
		for(Property property : properties) {
			String name = property.getName();
			Method method = derivedClass.getMethod(Utils.getterOf(name));
			WriterTypes tw = WriterTypes.writerfor(property.getClassName());
			property.setField("writer", tw);
			property.setField("getter", method);
		}
	}

	@Override
	public void write(StreamWriter writer, Object obj) throws Exception {
		for(Property property : properties) {
			WriterTypes type = property.getField("writer");
			Method method = property.getField("getter");
			String name = property.getName();
			Object value = method.invoke(obj);
			type.write(writer, name, value);
		}
	}
	
}
