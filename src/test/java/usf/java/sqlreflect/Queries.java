package usf.java.sqlreflect;

import java.text.SimpleDateFormat;

import usf.java.sqlreflect.sql.ParameterFactory;
import usf.java.sqlreflect.sql.Parameters;

public interface Queries {
	
	String query = "SELECT 1";
	String query1 = "SELECT * FROM city where CountryCode=? and District=?";
	

	
}
