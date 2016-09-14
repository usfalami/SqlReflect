package usf.java.sqlreflect.sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.field.Query;

public class SqlUtils {
	
	public static void bindPreparedStatement(PreparedStatement pstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++)
			set(pstmt, i+1, args[i]);
	}
	
	public static void bindCallableStatement(CallableStatement cstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++){
			Parameter<?> arg = args[i];
			if(arg.isOut())
				cstmt.registerOutParameter(i+1, arg.getSqlType());
			else
				set(cstmt, i+1, arg);
		}
	}
	
	public static void updateOutParameter(CallableStatement cstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++){
			Parameter arg = args[i];
			if(arg.isOut())
				arg.setValue(cstmt.getObject(i+1));
		}
	}

	public static void buildBatch(Statement stmt, Query... queries) throws SQLException {
		if(queries == null || queries.length == 0) throw new SQLException("one query at least");
		for(Query query : queries)
			stmt.addBatch(query.getSQL());
	}

	public static void buildBatch(PreparedStatement pstmt, Parameters... argList) throws SQLException {
		for(Parameters args : argList){
			bindPreparedStatement(pstmt, args.toArray(new Parameter[args.size()]));
			pstmt.addBatch();
		}
	}
	
	private static void set(PreparedStatement pstmt, int index, Parameter<?> arg) throws SQLException{
		if(arg.getValue() == null) pstmt.setNull(index, arg.getSqlType());
		else pstmt.setObject(index, arg.getValue(), arg.getSqlType());
	}
}
