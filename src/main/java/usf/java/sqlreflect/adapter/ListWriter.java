package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;

public class ListWriter<T> implements Adapter<T> {

	private StreamWriter sw;
	private Template<T> template;

	public ListWriter(StreamWriter stream) {
		this.sw = stream;
	}

	@Override
	public void start() throws Exception { }
	
	@Override
	public void prepare(Template<T> template) throws Exception {
		this.template = template;
		sw.startList(template.getClass().getSimpleName(), template.getFields());
	}

	@Override
	public void adapte(T field, int index) throws Exception {
		template.write(sw, field);
	}

	@Override
	public void end(ActionTimer time) throws Exception {
		sw.endList();
	}
	
	public StreamWriter getStream() {
		return sw;
	}
	
}