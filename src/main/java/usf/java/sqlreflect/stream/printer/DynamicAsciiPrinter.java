package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import usf.java.sqlreflect.reflect.Utils;

public class DynamicAsciiPrinter extends AsciiPrinter implements Printer {
	
	private List<String[]> entries;
	private String[] headers;
	private int lengths[], row, col;
	
	public DynamicAsciiPrinter(OutputStream out) {
		super(out);
	}
	
	@Override
	public void setHeaders(String... headers){
		this.headers = headers;
		entries = new ArrayList<String[]>();
		lengths = new int[headers.length];
		for(int i=0; i<headers.length; i++)
			lengths[i] = headers[i].length();
	}

	@Override
	public void appendRow(){
		entries.add(new String[headers.length]);
		row = entries.size() - 1;
		col = 0;
	}

	@Override
	public void appendColumn(String value){
		entries.get(row)[col] = value;
		if(Utils.isNotNull(value))
			lengths[col] = Math.max(value.length(), lengths[col]);
		col++;
	}

	@Override
	public void print(){
		configure(true, lengths);
		startTable();
		formatHeaders((Object[])headers);
		startRows();
		for(int i=0; i<entries.size(); i++)
			formatRow((Object[])entries.get(i));
		endRows();
		endTable();
		entries.clear();
	}
}
