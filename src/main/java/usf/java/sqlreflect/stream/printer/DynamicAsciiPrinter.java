package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import usf.java.sqlreflect.reflect.Utils;

public class DynamicAsciiPrinter implements Printer {
	
	private List<String[]> entries;
	private String[] headers;
	private int lengths[], col;
	
	private MultipleSizeAsciiPrinter ascii;
	
	public DynamicAsciiPrinter(OutputStream out) {
		ascii = new MultipleSizeAsciiPrinter(out);
	}
	
	@Override
	public void startTable(String... headers){
		entries = new ArrayList<String[]>();
		this.headers = headers;
		lengths = new int[headers.length];
		for(int i=0; i<headers.length; i++)
			lengths[i] = headers[i].length();
	}
	
	@Override
	public void endTable() {
		ascii.setSizes(lengths);
		ascii.startTable(headers);
		for(String[] list : entries){
			ascii.startRow();
			for(String col : list) ascii.addColumn(col);
			ascii.endRow();
		}
		ascii.endTable();
		clear();
	}

	@Override
	public void startRow(){
		entries.add(new String[headers.length]);
		col = 0;
	}
	
	@Override
	public void endRow() { }

	@Override
	public void addColumn(String value){
		int row = entries.size() - 1;
		if(Utils.isNotNull(value)){
			entries.get(row)[col] = value;
			lengths[col] = Math.max(value.length(), lengths[col]);
		}
		col++;
	}
	
	protected void clear(){
		entries.clear();
		headers = null;
		lengths = null;
		col = 0;
	}
	
}
