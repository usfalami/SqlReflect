package usf.java.sqlreflect.server;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.SqlQuery;
import usf.java.sqlreflect.sql.item.Callable;

public interface Server {

	String getDriver();
	
	String buildURL(Env env);
	
	Callable parseCallable(String sql);
	
	SqlQuery parseQuery(String sql);
	
	ResultSet getDatabase(DatabaseMetaData dm, String databasePattern) throws SQLException;
	ResultSet getTables(DatabaseMetaData dm, String databasePattern, String tablePattern, String... types) throws SQLException;
	ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String tablePattern, String columnPattern) throws SQLException;
	ResultSet getPrimaryKes(DatabaseMetaData dm, String databasePattern, String tablePattern) throws SQLException;
	ResultSet getProcedures(DatabaseMetaData dm, String databasePattern, String procedurePattern) throws SQLException;
	ResultSet getArguments(DatabaseMetaData dm, String databasePattern, String procedurePattern, String Attributespattern) throws SQLException;
}
