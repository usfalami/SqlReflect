package usf.java.sql.adapter.reflect.scanner;

import java.util.ArrayList;
import java.util.List;

import usf.java.sql.core.mapper.Mapper;

public class ScannerListMapper<T> extends AbstractScannerAdapter<T> {

	protected List<T> list;
	
	public ScannerListMapper(Mapper<T> mapper) {
		super(mapper);
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
