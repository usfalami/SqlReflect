package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import usf.java.sqlreflect.reflect.Utils;

public class DynamicAsciiPrinter implements Printer {
	
	private List<String[]> entries;
	private String[] headers;
	private int lengths[], row, col;
	
	private MultipleSizeAsciiPrinter ascii;
	
	public DynamicAsciiPrinter(OutputStream out) {
		ascii = new MultipleSizeAsciiPrinter(out);
	}
	
	@Override
	public void startList(String... headers){
		this.headers = headers;
		entries = new ArrayList<String[]>();
		lengths = new int[headers.length];
		for(int i=0; i<headers.length; i++)
			lengths[i] = headers[i].length();
	}
	
	@Override
	public void endList() {
		ascii.setSizes(lengths);
		ascii.startList(headers);
		for(String[] list : entries){
			ascii.startObject();
			for(String col : list)
				ascii.addColumn(col);
			ascii.endObject();
		}
		ascii.endList();
	}

	@Override
	public void startObject(){
		entries.add(new String[headers.length]);
		row = entries.size() - 1;
		col = 0;
	}
	
	@Override
	public void endObject() {
		
	}

	@Override
	public void addColumn(Object obj){
		if(Utils.isNull(obj))
			entries.get(row)[col] = "";
		else{
			String value  = obj.toString();
			entries.get(row)[col] = value;
			if(Utils.isNotNull(value))
				lengths[col] = Math.max(value.length(), lengths[col]);
		}
		col++;
	}
	
	
}
