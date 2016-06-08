package usf.java.sql.db.field;


public interface SQL extends Field {
	
	public String getQuery();
	public void setSql(String query);

	public String getDatabase();
	public void setDatabase(String database);
	
	public String getName() ;
	public void setName(String name);
	
	public String getType() ;
	public void setType(String type);

	void setParameters(String... parameters);
	String[] getParameters();
	
}
