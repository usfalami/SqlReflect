package usf.java.sqlreflect;

public interface Queries {

	String query 	= "SELECT 1";
	String query1 	= "SELECT * FROM city where CountryCode=? and District=?";
	String query2 	= "SELECT * FROM country where Capital=?";
	String query3 	= "SELECT * FROM country where code=?";

	String query4 = "INSERT INTO country(Code, Name, Capital, Code2) values(?,?,?,?)";
}
