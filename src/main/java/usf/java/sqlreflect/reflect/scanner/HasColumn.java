package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.ProcedureColumnMapper;
import usf.java.sqlreflect.mapper.TableColumnMapper;

public enum HasColumn {
	
	TABLE{
		@Override
		public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException {
			return dm.getColumns(null, databasePattern, parentPattern, columnPattern);
		}
		@Override
		public Mapper<Column> getMapper() {
			return new TableColumnMapper();
		}
	}, 
	
	PROCEDURE{
		@Override
		public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException {
			return dm.getProcedureColumns(null, databasePattern, parentPattern, columnPattern);
		}
		@Override
		public Mapper<Column> getMapper() {
			return new ProcedureColumnMapper();
		}
	};
	
	public abstract ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException;

	public abstract Mapper<Column> getMapper();
}
