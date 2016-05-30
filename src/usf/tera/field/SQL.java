package usf.tera.field;

import java.io.Serializable;

public interface SQL extends Field {
	
	public String getSql();
	public void setSql(String query);

	public String getSchema();
	public void setSchema(String schema);
	
	public String getName() ;
	public void setName(String name);
	
	void setParametersToBing(Serializable... params);
	Serializable[] getParametersToBing();

	void setParameters(Parameter... parameters);
	Parameter[] getParameters();
	
}
