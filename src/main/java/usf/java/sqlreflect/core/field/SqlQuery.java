package usf.java.sqlreflect.core.field;

public class SqlQuery implements Query  {

	protected String query;

	public SqlQuery(String query) {
		this.query = query;
	}

	@Override
	public String getSQL() {
		return query;
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
