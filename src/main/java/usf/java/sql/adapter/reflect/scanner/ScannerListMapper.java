package usf.java.sql.adapter.reflect.scanner;

import java.util.ArrayList;
import java.util.List;

import usf.java.sql.core.reflect.scanner.Scanner.ScannerAdapter;

public class ScannerListMapper<T> implements ScannerAdapter<T> {

	protected List<T> list;
	
	public ScannerListMapper() {
		
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
