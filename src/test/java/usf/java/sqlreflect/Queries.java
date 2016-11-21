package usf.java.sqlreflect;

import java.util.List;

import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.ParameterFactory;
import usf.java.sqlreflect.sql.Parameters;

public interface Queries {

	String query 	= "SELECT 1";
	String query1 	= "SELECT * FROM city where CountryCode=? and District=?";
	String query2 	= "SELECT * FROM country where Capital=?";
	
	Object[] select_country_query_param		= new Object[]{"XYZ"};
	List<Parameter<?>> select_country_bind_Params = new Parameters(
			ParameterFactory.CHAR_WRAPPER("XYZ")
	);
	
	String select_country_query 		= "SELECT * FROM country where code='"+
			select_country_query_param[0] +"'";
	String select_country_bind_query 	= "SELECT * FROM country where code=?";
	
	Object[] insert_country_param		= new Object[]{"XYZ", "MyCountry", null, "ZZ"};
	List<Parameter<?>> insert_country_bind_Params = new Parameters(
			ParameterFactory.CHAR_WRAPPER((String)insert_country_param[0]),
			ParameterFactory.CHAR_WRAPPER((String)insert_country_param[1]),
			ParameterFactory.INTEGER_WRAPPER(insert_country_param[2] == null ? null : (Integer)insert_country_param[1]),
			ParameterFactory.CHAR_WRAPPER((String)insert_country_param[3])
	);
	
	String insert_country_query 		= "INSERT INTO country(Code, Name, Capital, Code2) values('"+
			insert_country_param[0]+"','"+
			insert_country_param[1]+"',"+
			insert_country_param[2]+",'"+
			insert_country_param[3]+"')";
	String insert_country_bind_query 	= "INSERT INTO country(Code, Name, Capital, Code2) values(?,?,?,?)";

}
