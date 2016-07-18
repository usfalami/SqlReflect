package usf.java.sql.core.adapter;

import java.util.ArrayList;
import java.util.List;

import usf.java.sql.core.reflect.scanner.Scanner.ScannerAdapter;

public class ListAdapter<T> implements ScannerAdapter<T> {

	protected List<T> list;
	
	public ListAdapter() {
		
	}
	
	@Override
	public void headers(String... headers) {
		
	}

	@Override
	public void start() {
		list = new ArrayList<T>();
	}

	@Override
	public void adapte(T field, int index) {
		list.add(field);
	}

	@Override
	public void end() {

	}
	
	public List<T> getList() {
		return list;
	}
}
