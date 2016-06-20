package usf.java.sql.adapter.reflect.scanner;

import java.util.ArrayList;
import java.util.List;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Field;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.scanner.Scanner.HasScanner;

public class SimpleFieldListAdapter<T extends Field> extends AbstractScannerAdapter<T> implements HasScanner<T> {

	protected List<T> list;
	
	public SimpleFieldListAdapter(ConnectionManager cm, Mapper<T> mapper) {
		super(cm, mapper, null);
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
