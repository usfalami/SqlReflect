package usf.java.sql.core.field.types;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum HasColumn {
	
	TABLE{
		@Override
		public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException {
			return dm.getProcedureColumns(null, databasePattern, parentPattern, columnPattern);
		}
	}, 
	
	PROCEDURE{
		@Override
		public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException {
			return dm.getColumns(null, databasePattern, parentPattern, columnPattern);
		}
	};
	
	public abstract ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException;
}
