package usf.tera.reflect.adpter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Adapter {

	DateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	int COLUMN_NUM_LENGTH   =-2;
	int COLUMN_NAME_LENGTH  =-30;
	int COLUMN_TYPE_LENGTH  =-10;
	int COLUMN_SIZE_LENGTH  =-8;
	int COLUMN_PARAM_LENGTH =-60;
	
	
	String SCHEM_FORMAT = "Schema : %s \n";
	String PRROC_FORMAT = "Procedure : %s \n";
	
	String PARAM_FORMAT = "|%"+COLUMN_NUM_LENGTH+"s| %"+COLUMN_NAME_LENGTH+"s| %"+COLUMN_TYPE_LENGTH+"s| %"+COLUMN_SIZE_LENGTH+"s|";
	String PARAM_VALUE_FORMAT = PARAM_FORMAT+" %"+COLUMN_PARAM_LENGTH+"s|";
	
	String COLUMN = String.format(PARAM_FORMAT, "N°", "Name", "Type", "Size");
	String COLUMN_VALUE = String.format(PARAM_VALUE_FORMAT, "N°", "Name", "Type", "Size", "Value");
	
	String CADRE = String.format("+%"+(COLUMN.length()-2)+"s+\n","").replace(" ", "-");
	String CADRE_PARAM = String.format("+%"+(COLUMN_VALUE.length()-2)+"s+\n","").replace(" ", "-");
	
	
	public static class Utils{
		
	}
	
}
