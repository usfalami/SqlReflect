package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.item.Argument;
import usf.java.sqlreflect.mapper.ColumnProcedureMapper;
import usf.java.sqlreflect.mapper.ColumnTableMapper;
import usf.java.sqlreflect.mapper.Mapper;

public enum SourceTypes {
	
	TABLE{
		@Override
		public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException {
			return dm.getColumns(null, databasePattern, parentPattern, columnPattern);
		}
		@Override
		public Mapper<Argument> getMapper() {
			return new ColumnTableMapper();
		}
	}, 
	
	PROCEDURE{
		@Override
		public ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException {
			return dm.getProcedureColumns(null, databasePattern, parentPattern, columnPattern);
		}
		@Override
		public Mapper<Argument> getMapper() {
			return new ColumnProcedureMapper();
		}
	};
	
	public abstract ResultSet getColumns(DatabaseMetaData dm, String databasePattern, String parentPattern, String columnPattern) throws SQLException;

	public abstract Mapper<Argument> getMapper();
}
