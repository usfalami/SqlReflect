package usf.tera.reflect.adpter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Adapter {

	DateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	//Custom parameters
	
	int COLUMN_NUM_LENGTH   =-2;
	int COLUMN_NAME_LENGTH  =-30;
	int COLUMN_TYPE_LENGTH  =-10;
	int COLUMN_SIZE_LENGTH  =-8;
	int COLUMN_PARAM_LENGTH =-60;
	
	//Format
	
	String SCHEM_FORMAT = "Schema : %s\n";
	String PRROC_FORMAT = "Procedure : %s\n";
	
	String COLUMN_FORMAT = "|%"+COLUMN_NUM_LENGTH+"s| %"+COLUMN_NAME_LENGTH+"s| %"+COLUMN_TYPE_LENGTH+"s| %"+COLUMN_SIZE_LENGTH+"s|\n";
	String COLUMN_PARAM_FORMAT = COLUMN_FORMAT.replace('\n', ' ')+"%"+COLUMN_PARAM_LENGTH+"s|\n";
	
	
	String COLUMN = String.format(COLUMN_FORMAT, "N°", "Name", "Type", "Size");
	String COLUMN_PARAM = String.format(COLUMN_PARAM_FORMAT, "N°", "Name", "Type", "Size", "Value");
	
	String CADRE = String.format("+%"+(COLUMN.length()-3)+"s+\n","").replace(" ", "-");
	String CADRE_PARAM = String.format("+%"+(COLUMN_PARAM.length()-3)+"s+\n","").replace(" ", "-");
	
	
	public static class Utils{
		
	}
	
}
