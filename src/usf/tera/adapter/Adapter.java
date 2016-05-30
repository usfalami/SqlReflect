package usf.tera.adapter;

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

}
