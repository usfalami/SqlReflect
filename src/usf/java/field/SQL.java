package usf.java.field;


public interface SQL extends Field {
	
	public String getSql();
	public void setSql(String query);

	public String getSchema();
	public void setSchema(String schema);
	
	public String getName() ;
	public void setName(String name);

	void setParameters(String... parameters);
	String[] getParameters();
	
}
