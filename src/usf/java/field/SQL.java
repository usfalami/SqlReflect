package usf.java.field;


public interface SQL extends Field {
	
	public String Query();
	public void setSql(String query);

	public String getSchema();
	public void setSchema(String schema);
	
	public String getName() ;
	public void setName(String name);
	
	public String getType() ;
	public void setType(String type);

	void setParameters(String... parameters);
	String[] getParameters();
	
}
