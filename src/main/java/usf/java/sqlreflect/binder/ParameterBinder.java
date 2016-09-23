package usf.java.sqlreflect.binder;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.type.ParameterTypes;

public class ParameterBinder implements Binder<List<Parameter<?>>> {

	@Override
	public void bindPreparedStatement(PreparedStatement pstmt, List<Parameter<?>> args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.size(); i++)
			Utils.set(pstmt, i+1, args.get(i));
	}
	
	@Override
	public void bindCallableStatement(CallableStatement cstmt, List<Parameter<?>> args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.size(); i++){
			Parameter<?> arg = args.get(i);
			if(ParameterTypes.isOut(arg.getType())) //Parameter can be inout type
				cstmt.registerOutParameter(i+1, arg.getSqlType());
			if(ParameterTypes.isIN(arg.getType()))
				Utils.set(cstmt, i+1, arg);
		}
	}

	@Override
	public void updateOutParameter(CallableStatement cstmt, List<Parameter<?>> args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.size(); i++){
			Parameter arg = args.get(i);
			if(ParameterTypes.isOut(arg.getType()))
				arg.setValue(cstmt.getObject(i+1));
		}
	}
}