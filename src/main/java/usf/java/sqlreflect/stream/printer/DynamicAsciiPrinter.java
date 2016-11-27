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
	public void startList(String... headers){
		entries = new ArrayList<String[]>();
		lengths = new int[headers.length];
		this.headers = headers;
		for(int i=0; i<headers.length; i++)
			lengths[i] = headers[i].length();
	}
	
	@Override
	public void endList() {
		ascii.setSizes(lengths);
		ascii.startList(headers);
		for(String[] list : entries){
			ascii.startObject();
			for(String col : list) ascii.addColumn(col);
			ascii.endObject();
		}
		ascii.endList();
		clear();
	}

	@Override
	public void startObject(){
		entries.add(new String[headers.length]);
		col = 0;
	}
	
	@Override
	public void endObject() { }

	@Override
	public void addColumn(Object obj){
		int row = entries.size() - 1;
		if(Utils.isNotNull(obj)){
			String value = obj.toString();
			lengths[col] = Math.max(value.length(), lengths[col]);
			entries.get(row)[col] = value;
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
