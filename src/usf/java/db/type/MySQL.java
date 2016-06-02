package usf.java.db.type;

import java.io.Serializable;

import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.field.Macro;
import usf.java.field.Procedure;
import usf.java.field.Query;

public class MySQL implements Database {

	@Override
	public String getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String makeURL(Env env) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Macro parseMacro(String sql, Serializable... parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Procedure parseProcedure(String sql, Serializable... parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query parseQuery(String sql, Serializable... parameters) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
