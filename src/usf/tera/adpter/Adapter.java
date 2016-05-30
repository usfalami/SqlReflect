package usf.tera.adpter;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Adapter {

	DateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	DateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");
	
	//Custom parameters
	
	int COLUMN_NUM_LENGTH   =-2;
	int COLUMN_NAME_LENGTH  =-30;
	int COLUMN_TYPE_LENGTH  =-10;
	int COLUMN_SIZE_LENGTH  =-8;
	int COLUMN_PARAM_LENGTH =-60;
	
	int PERFORM_TEXT_LENGTH =-10;
	
	int VALUE_LENGTH =-21;
	
	
	public static class Formatter{

		public PrintStream out;
		
		String format;
		String layout;
		
		public Formatter(PrintStream out, int cols, int size) {
	        this.out=out;
			StringBuilder s = new StringBuilder();
			for(int i=0; i<cols; i++)
				s.append("| %").append(size).append("s");
			format = s.append("|\n").toString();
			size=Math.abs(size);
			layout = String.format("+%"+(cols*(size+2)-1)+"s+\n","").replace(' ', '-');
		}
		public Formatter(PrintStream out, int ...sizes) {
	        this.out=out;
			StringBuilder s = new StringBuilder();
			int max=0;
			for(int i=0; i<sizes.length; i++){
				s.append("| %").append(sizes[i]).append("s");
				max+=Math.abs(sizes[i])+2;
			}
			format = s.append("|\n").toString();
			layout = String.format("+%"+(max-1)+"s+\n","").replace(' ', '-');			
		}
		
		public void startTable(){
			out.println();
		}
		public void formatTitle(String title){
			out.print(layout);
			out.format("| %"+(4-layout.length())+"s|\n", title);
		}
		public void formatHeaders(Object ...obj){
			out.print(layout);
			out.format(format, obj);
			out.print(layout);
		}
		public void formatRow(Object ...obj){
			out.format(format, obj);
		}
		public void endTable(){
			out.print(layout);
		}
	}
	
	
}
