package usf.java.sqlreflect.sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtils {
	
	public static void bindPreparedStatement(PreparedStatement pstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++)
			pstmt.setObject(i+1, args[i].getValue());
	}
	
	public static void bindCallableStatement(CallableStatement cstmt, Parameter<?>... args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++){
			Parameter<?> arg = args[i];
			if(arg.isOut())
				cstmt.registerOutParameter(i+1, arg.getSqlType());
			else
				cstmt.setObject(i+1, arg.getValue());
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


}
