package usf.java.sqlreflect.adapter;

import java.util.ArrayList;
import java.util.List;

import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.TimePerform;

public class ListAdapter<T> implements Adapter<T> {

	protected List<T> list;
	
	public ListAdapter() {
		list = new ArrayList<T>();
	}

	@Override
	public void start() {
		
	}

	@Override
	public void prepare(Mapper<T> mapper) {
		
	}
	
	@Override
	public void adapte(T field, int index) {
		list.add(field);
	}

	@Override
	public void end(TimePerform time) {

	}
	
	public List<T> getList() {
		return list;
	}
}
