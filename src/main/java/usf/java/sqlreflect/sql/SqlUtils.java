package usf.java.sqlreflect.sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class SqlUtils {
	
	public static final String[] columnNames(ResultSet rs) throws SQLException {
		ResultSetMetaData rm = rs.getMetaData();
		String[] columns = new String[rm.getColumnCount()];
		for(int i=0; i<columns.length; i++)
			columns[i] = rm.getColumnName(i+1);
		return columns;
	}
	
	public static void bindPreparedStatement(PreparedStatement pstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++)
			set(pstmt, i+1, args[i]);
	}
	
	public static void bindCallableStatement(CallableStatement cstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++){
			Parameter<?> arg = args[i];
			if(ParameterTypes.isOut(arg.getType())) //Parameter can be inout type
				cstmt.registerOutParameter(i+1, arg.getSqlType());
			if(ParameterTypes.isIN(arg.getType()))
				set(cstmt, i+1, arg);
		}
	}
	
	public static void updateOutParameter(CallableStatement cstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++){
			Parameter arg = args[i];
			if(ParameterTypes.isOut(arg.getType()))
				arg.setValue(cstmt.getObject(i+1));
		}
	}

	public static void buildBatch(Statement stmt, Runnable... queries) throws SQLException {
		if(queries == null || queries.length == 0) throw new SQLException("one query at least");
		for(Runnable query : queries)
			stmt.addBatch(query.asQuery());
	}

	public static void buildBatch(PreparedStatement pstmt, Parameters... argList) throws SQLException {//TODO test parameters before use it
		for(Parameters args : argList){
			bindPreparedStatement(pstmt, args.toArray());
			pstmt.addBatch();
		}
	}
	
	private static void set(PreparedStatement pstmt, int index, Parameter<?> arg) throws SQLException {
		if(arg == null) pstmt.setNull(index, Types.NULL); //TODO check that
		else if(arg.getValue() == null) pstmt.setNull(index, arg.getSqlType());
		else pstmt.setObject(index, arg.getValue(), arg.getSqlType());
	}
}
