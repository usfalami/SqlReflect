package usf.java.sqlreflect.server;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import usf.java.sqlreflect.sql.SqlQuery;
import usf.java.sqlreflect.sql.entry.item.Callable;
import usf.java.sqlreflect.sql.entry.item.Macro;
import usf.java.sqlreflect.sql.entry.item.Procedure;
import usf.java.sqlreflect.sql.type.ServerConstants;

public class MySqlServer implements Server {

	
	private static final String URL_TEMPLATE = "jdbc:mysql://%s:%d/%s";

	private static final String FUNCTION_PATTERN = "(?i)^(call|exec|execute)\\s*(\\w+)\\.(\\w+)\\s*\\((.+)\\)$";
	
	@Override
	public String buildURL(Env env) {
		return String.format(URL_TEMPLATE, env.getHost(), env.getPort(), env.getDatabase());
	}
	@Override
	public String getDriver() {
		return "com.mysql.jdbc.Driver";
	}
	
	@Override
	public Callable parseCallable(String sql) {
		Callable callable = null;
		if(sql.matches(FUNCTION_PATTERN)){
			Pattern p = Pattern.compile(FUNCTION_PATTERN);
			Matcher m = p.matcher(sql.trim());
			if(m.matches()){
				callable = m.group(1).toLowerCase().equals("call") ? new Procedure(sql) : new Macro(sql);
				callable.setDatabaseName(m.group(2)); 
				callable.setName(m.group(3));
				callable.setParameters(m.group(4).split("\\s*,\\s*")); //TODO "," 
			}
		}
		return callable;
	}
	
	@Override
	public SqlQuery parseQuery(String sql) {
		return new SqlQuery(sql);
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
	
	@Override
	public ServerConstants getType() {
		return ServerConstants.CATALOG;
	}
}
