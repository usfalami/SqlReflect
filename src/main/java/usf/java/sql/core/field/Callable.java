package usf.java.sql.core.field;

public interface Callable extends Field {

	public String getDatabase();
	public void setDatabase(String database);
	
	public String getName() ;
	public void setName(String name);

	public String getSQL();

	void setParameters(String... parameters);
	String[] getParameters();
	
}
