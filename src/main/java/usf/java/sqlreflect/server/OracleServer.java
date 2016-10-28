package usf.java.sqlreflect.server;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.type.DatabaseType;

public class OracleServer implements Server {

	@Override
	public String getDriver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getURL(Env env) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getDatabase(DatabaseMetaData dm, String databasePattern) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getTables(DatabaseMetaData dm, String databasePattern, String tablePattern, String... types)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String tablePattern, String columnPattern)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getPrimaryKes(DatabaseMetaData dm, String databasePattern, String tablePattern)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getProcedures(DatabaseMetaData dm, String databasePattern, String procedurePattern)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getArguments(DatabaseMetaData dm, String databasePattern, String procedurePattern, String argumentPattern) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResultSet getFunction(DatabaseMetaData dm, String databasePattern, String functionPattern) throws SQLException {
		return null;
	}
	
	@Override
	public DatabaseType getDatabaseType() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
