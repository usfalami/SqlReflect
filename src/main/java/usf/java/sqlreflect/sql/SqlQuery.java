package usf.java.sqlreflect.sql;

public class SqlQuery implements Runnable {

	private String query;

	public SqlQuery(String query) {
		this.query = query;
	}

	@Override
	public String asQuery() {
		return query;
	}

}
