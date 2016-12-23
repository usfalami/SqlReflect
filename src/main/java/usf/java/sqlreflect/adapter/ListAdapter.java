package usf.java.sqlreflect.adapter;

import java.util.ArrayList;
import java.util.Collection;

import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.reflect.ActionTimer;

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
	public void prepare(Collection<Metadata> metadata, Class<T> clazz) {
		
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
