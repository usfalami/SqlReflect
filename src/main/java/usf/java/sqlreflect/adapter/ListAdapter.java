package usf.java.sqlreflect.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.Header;

public class ListAdapter<T> implements Adapter<T> {

	private Collection<T> list;
	private ActionTimer timer;
	
	public ListAdapter() {
		this.list = new ArrayList<T>();
	}
	
	@Override
	public void start() {
		
	}

	@Override
	public void prepare(List<Header> headers, Class<T> clazz) {
		
	}
	
	@Override
	public void adapte(T field, int index) {
		list.add(field);
	}

	@Override
	public void end(ActionTimer timer) {
		this.timer = timer;
	}
	
	public Collection<T> getList() {
		return list;
	}
	public ActionTimer getActionTimer() {
		return timer;
	}
	
}
