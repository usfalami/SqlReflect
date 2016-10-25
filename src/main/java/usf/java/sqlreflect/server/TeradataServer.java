package usf.java.sqlreflect.server;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.type.ServerConstants;

public class TeradataServer implements Server {
	
	private static final String URL_TEMPLATE = "jdbc:teradata://%s/database=%s,dbs_port=%d,%s";
	
	@Override
	public String getURL(Env env) {
		return String.format(URL_TEMPLATE, env.getHost(), env.getDatabase(), env.getPort(), env.getParams());
	}
	@Override
	public String getDriver() {
		return "com.teradata.jdbc.TeraDriver";
	}
	
	@Override
	public ServerConstants getType() {
		return ServerConstants.SCHEMA;
	}
	
	@Override
	public ResultSet getDatabase(DatabaseMetaData dm, String databasePattern) throws SQLException {
		return dm.getSchemas();
	}
	@Override
	public ResultSet getTables(DatabaseMetaData dm, String databasePattern, String tablePattern, String... types) throws SQLException {
		return dm.getTables(null, databasePattern, tablePattern, types);
	}
	@Override
	public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String tablePattern, String columnPattern) throws SQLException {
		return dm.getColumns(null, databasePattern, tablePattern, columnPattern);
	}
	@Override
	public ResultSet getPrimaryKes(DatabaseMetaData dm, String databasePattern, String tablePattern) throws SQLException {
		return dm.getPrimaryKeys(null, databasePattern, tablePattern);
	}
	@Override
	public ResultSet getProcedures(DatabaseMetaData dm, String databasePattern, String procedurePattern) throws SQLException {
		return dm.getProcedures(null, databasePattern, procedurePattern);
	}
	@Override
	public ResultSet getArguments(DatabaseMetaData dm, String databasePattern, String procedurePattern, String argumentPattern) throws SQLException {
		return dm.getProcedureColumns(null, databasePattern, procedurePattern, argumentPattern);
	}
	@Override
	public ResultSet getFunction(DatabaseMetaData dm, String databasePattern, String functionPattern) throws SQLException {
		return dm.getFunctions(null, databasePattern, functionPattern);
	}
	
}