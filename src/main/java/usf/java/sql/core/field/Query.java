package usf.java.sql.core.field;

public class Query implements SQL  {
	
	protected String query;

	public Query(String query) {
		this.query = query;
	}

	@Override
	public String get() {
		return query;
	}

	@Override
	public void set(String query) {
		this.query = query;
	}

	@Override
	public String getDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDatabase(String database) {
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
}
