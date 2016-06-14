package usf.java.sql.core.db.field;

public interface SQL extends Field {


	public String getDatabase();
	public void setDatabase(String database);
	
	public String getName() ;
	public void setName(String name);
	
	public String getType() ;
	public void setType(String type);
	
	public String get();
	public void set(String sql);

	void setParameters(String... parameters);
	String[] getParameters();
	
}
