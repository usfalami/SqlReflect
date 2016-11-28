package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.ActionTimerMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;

public class FullWriter<T> extends ListWriter<T> {
	
	public FullWriter(StreamWriter writer) {
		super(writer);
	}
	
	@Override
	public void end(ActionTimer time) throws Exception {
		super.end(time);
		Mapper<ActionTimer> mapper = new ActionTimerMapper();
		getWriter().start("LIST");
		getWriter().startList("LIST", mapper.getColumnNames());
		mapper.write(getWriter(), time);
		getWriter().endList();
		getWriter().end();
	}
}