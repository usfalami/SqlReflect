package usf.java.sqlreflect;

import java.util.List;

import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.ParameterFactory;
import usf.java.sqlreflect.sql.Parameters;
import usf.java.sqlreflect.sql.entry.GenericType;

public interface Queries {

	String query1 	= "SELECT * FROM city where CountryCode=? and District=?";

	Object[] select_country_result_1				= new Object[]{"MAR", "Morocco", 2486, "MA"};
	List<Parameter<?>> select_country_bind_Params_1 = new Parameters(
			ParameterFactory.CHAR_WRAPPER(select_country_result_1[0].toString())
	);
	Object[] select_country_result_2				= new Object[]{"XYZ", "MyCountry", null, "ZZ"};
	List<Parameter<?>> select_country_bind_Params_2 = new Parameters(
			ParameterFactory.CHAR_WRAPPER(select_country_result_2[0].toString())
	);
	GenericType select_country_bind_Params_3 = new GenericType().set("code", select_country_result_1[0]);
	String select_country_query 		= "SELECT * FROM country where code=%s";
	
	Object[] insert_country_result		= new Object[]{"XYZ", "MyCountry", null, "ZZ"};
	List<Parameter<?>> insert_country_bind_params = new Parameters(
			ParameterFactory.CHAR_WRAPPER((String)insert_country_result[0]),
			ParameterFactory.CHAR_WRAPPER((String)insert_country_result[1]),
			ParameterFactory.INTEGER_WRAPPER(insert_country_result[2] == null ? null : (Integer)insert_country_result[1]),
			ParameterFactory.CHAR_WRAPPER((String)insert_country_result[3])
	);
	String insert_country_query 	= "INSERT INTO country(Code, Name, Capital, Code2) values(%s,%s,%s,%s)";
	
	
	public static class Helper {
		
		public static String build(String query, Object... args){
			if(Utils.isEmptyString(query)) return null;
			if(Utils.isEmptyArray(args)) return query.replace("%s", "?");
			Object[] params = new String[args.length];
			for(int i=0; i<params.length; i++)
				params[i] = args[i] == null ? null : 
					args[i] instanceof String ? "'" + args[i] + "'" : args[i].toString();
			return String.format(query, params);
		}
		
		
	}

}
