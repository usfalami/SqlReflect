package usf.java.sqlreflect.writer;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.stream.StreamWriter;

public class ObjectReflectWriter implements Writer<Object> {
	
	private Map<String, MethodTypeWriter> methodMap;
	
	public ObjectReflectWriter() {
		methodMap = new HashMap<String, MethodTypeWriter>();
	}

	@Override
	public <D extends Object> void prepare(Class<D> derivedClass, Collection<Metadata> metadatas) throws SQLException {
		try {
			for(Metadata metadata : metadatas) {
				String name = metadata.getPropertyName();
				Method method = derivedClass.getMethod(Utils.getterOf(name));
				TypeWriter tw = TypeWriter.writerfor(metadata.getColumnClassName());
				methodMap.put(name, new MethodTypeWriter(tw, method));
			}
		} catch (Exception  e) {
			e.printStackTrace();
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
		
		TypeWriter tw;
		Method method;
		
		public MethodTypeWriter(TypeWriter tw, Method method) {
			this.tw = tw;
			this.method = method;
		}
	}

}
