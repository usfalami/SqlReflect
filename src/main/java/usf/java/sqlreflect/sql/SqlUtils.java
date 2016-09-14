package usf.java.sqlreflect.sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.field.Query;

public class SqlUtils {
	
	public static void bindPreparedStatement(PreparedStatement pstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++){
			Parameter<?> arg = args[i];
			pstmt.setObject(i+1, arg.getValue(), arg.getSqlType());
		}
	}
	
	public static void bindCallableStatement(CallableStatement cstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++){
			Parameter<?> arg = args[i];
			if(arg.isOut())
				cstmt.registerOutParameter(i+1, arg.getSqlType());
			else
				cstmt.setObject(i+1, arg.getValue(), arg.getSqlType());
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

	public static  void buildBatch(PreparedStatement pstmt, Parameters... argList) throws SQLException {
		for(Parameters args : argList){
			for(int i=0; i<args.size(); i++)
				pstmt.setObject(i+1, args.get(i));
			pstmt.addBatch();
		}
	}

}
