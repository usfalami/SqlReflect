package usf.java.sqlreflect.writer;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.stream.StreamWriter;

public class ObjectReflectWriter implements Writer<Object> {
	
	private Map<String, MethodTypeWriter> methodMap;
	
	public ObjectReflectWriter() {
		methodMap = new HashMap<String, MethodTypeWriter>();
	}

	@Override
	public <D extends Object> void prepare(Class<D> derivedClass, Collection<Property> Properties) throws Exception {
		for(Property property : Properties) {
			String name = property.getName();
			Method method = derivedClass.getMethod(Utils.getterOf(name));
			WriterTypes tw = WriterTypes.writerfor(property.getClassName());
			methodMap.put(name, new MethodTypeWriter(tw, method));
		}
	}

	@Override
	public void write(StreamWriter writer, Object obj) throws Exception {
		for(Entry<String, MethodTypeWriter> entry : methodMap.entrySet()){
			MethodTypeWriter mw = entry.getValue();
			Object value = mw.method.invoke(obj);
			mw.tw.write(writer, entry.getKey(), value);
		}
	}
	
	private static class MethodTypeWriter {
		
		WriterTypes tw;
		Method method;
		
		public MethodTypeWriter(WriterTypes tw, Method method) {
			this.tw = tw;
			this.method = method;
		}
	}

}
