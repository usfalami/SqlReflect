package usf.java.sqlreflect.item;

import usf.java.sqlreflect.sql.Query;

public class SqlQuery implements Query  {

	protected String query;

	public SqlQuery(String query) {
		this.query = query;
	}

	@Override
	public String asQuery() {
		return query;
	}

}
