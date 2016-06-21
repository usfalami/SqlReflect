package usf.java.sql.core.field;

public interface Callable extends Field {

	String getDatabase();
	void setDatabase(String database);
	
	String getName() ;
	void setName(String name);

	String getSQL();

	void setParameters(String... parameters);
	String[] getParameters();
	
}
