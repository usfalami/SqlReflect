package usf.java.sqlreflect.field;

public interface Query extends Field {

	String getDatabaseName();
	void setDatabaseName(String database);
	
	String getName() ;
	void setName(String name);

	String getSQL();

	void setParameters(String... parameters);
	String[] getParameters();
	
}
