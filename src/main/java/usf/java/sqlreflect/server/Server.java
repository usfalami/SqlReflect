package usf.java.sqlreflect.server;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.type.DatabaseType;

public interface Server {

	String getDriver();
	
	String getURL(Env env);

	DatabaseType getDatabaseType();
	
	ResultSet getDatabase(DatabaseMetaData dm, String databasePattern) throws SQLException;
	ResultSet getTables(DatabaseMetaData dm, String databasePattern, String tablePattern, String... types) throws SQLException;
	ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String tablePattern, String columnPattern) throws SQLException;
	ResultSet getPrimaryKeys(DatabaseMetaData dm, String databasePattern, String tablePattern) throws SQLException;
	ResultSet getImportedKeys(DatabaseMetaData dm, String databasePattern, String tablePattern) throws SQLException;
	ResultSet getProcedures(DatabaseMetaData dm, String databasePattern, String procedurePattern) throws SQLException;
	ResultSet getArguments(DatabaseMetaData dm, String databasePattern, String procedurePattern, String argumentpattern) throws SQLException;
	ResultSet getFunction(DatabaseMetaData dm, String databasePattern, String functionPattern) throws SQLException;
	
}
