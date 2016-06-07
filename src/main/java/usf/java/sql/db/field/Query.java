package usf.java.sql.db.field;


public class Query implements SQL  {
	
	protected String query;

	public Query(String query) {
		this.query = query;
	}

	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public void setSql(String query) {
		this.query = query;
	}

	@Override
	public String getSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSchema(String schema) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Query";
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParameters(String... parameters) {
		// TODO Auto-generated method stub
	}

	@Override
	public String[] getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		
	}

}
