package usf.java.sqlreflect.server;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.type.DatabaseType;

public class MySqlServer implements Server {

	private static final String URL_TEMPLATE = "jdbc:mysql://%s:%d/%s?%s";

	@Override
	public String getURL(Env env) {
		return String.format(URL_TEMPLATE, env.getHost(), env.getPort(), env.getDatabase(), env.getParams());
	}
	@Override
	public String getDriver() {
		return "com.mysql.jdbc.Driver";
	}
	
	@Override
	public DatabaseType getDatabaseType() {
		return DatabaseType.CATALOG;
	}
	
	@Override
	public ResultSet getDatabase(DatabaseMetaData dm, String databasePattern) throws SQLException {
		return dm.getCatalogs();
	}
	@Override
	public ResultSet getTables(DatabaseMetaData dm, String databasePattern, String tablePattern, String... types) throws SQLException {
		return dm.getTables(databasePattern, null, tablePattern, types);
	}
	@Override
	public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String tablePattern, String columnPattern) throws SQLException {
		return dm.getColumns(databasePattern, null, tablePattern, columnPattern);
	}
	@Override
	public ResultSet getPrimaryKes(DatabaseMetaData dm, String databasePattern, String tablePattern) throws SQLException {
		return dm.getPrimaryKeys(databasePattern, null, tablePattern);
	}
	@Override
	public ResultSet getProcedures(DatabaseMetaData dm, String databasePattern, String procedurePattern) throws SQLException {
		return dm.getProcedures(databasePattern, null, procedurePattern);
	}
	@Override
	public ResultSet getArguments(DatabaseMetaData dm, String databasePattern, String procedurePattern, String argumentpattern) throws SQLException {
		return dm.getProcedureColumns(databasePattern, null, procedurePattern, argumentpattern);
	}
	@Override
	public ResultSet getFunction(DatabaseMetaData dm, String databasePattern, String functionPattern) throws SQLException {
		return dm.getFunctions(databasePattern, null, functionPattern);
	}

}
