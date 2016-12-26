package usf.java.sqlreflect.adapter;

import java.util.ArrayList;
import java.util.Collection;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.ActionTimer;

public class ListAdapter<T> implements Adapter<T> {

	private Collection<T> list;
	private ActionTimer timer;
	
	public ListAdapter() {
		this.list = new ArrayList<T>();
	}
	
	@Override
	public void start() { }

	@Override
	public void prepare(Class<T> clazz, Collection<Property> properies) { }
	
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
