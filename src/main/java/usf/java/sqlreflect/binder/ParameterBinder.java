package usf.java.sqlreflect.binder;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.type.ParameterTypes;

public class ParameterBinder implements Binder<List<Parameter<?>>> {

	@Override
	public void bind(Statement stmt, List<Parameter<?>> args) throws SQLException {
		if(Utils.isEmptyCollection(args)) return;
		if(stmt instanceof CallableStatement){
			CallableStatement cstmt = (CallableStatement)stmt;
			for(int i=0; i<args.size(); i++){
				Parameter<?> arg = args.get(i);
				if(ParameterTypes.isOut(arg.getType())) //Parameter can be inout type
					cstmt.registerOutParameter(i+1, arg.getSqlType());
				if(ParameterTypes.isIN(arg.getType()))
					set(cstmt, i+1, arg);
			}
		}
		else if(stmt instanceof PreparedStatement){
			PreparedStatement pstmt = (PreparedStatement)stmt;
			for(int i=0; i<args.size(); i++)
				set(pstmt, i+1, args.get(i));
		}
	}

	@Override
	public void post(Statement stmt, List<Parameter<?>> args) throws SQLException {
		if(!(stmt instanceof CallableStatement) || Utils.isEmptyCollection(args)) return;
		CallableStatement cstmt = (CallableStatement)stmt;
		for(int i=0; i<args.size(); i++){
			Parameter arg = args.get(i);
			if(ParameterTypes.isOut(arg.getType()))
				arg.setValue(cstmt.getObject(i+1));
		}
	}

	public static void set(PreparedStatement pstmt, int index, Parameter<?> arg) throws SQLException {
		if(Utils.isNull(arg)) pstmt.setNull(index, Types.NULL); //TODO check that
		else if(Utils.isNull(arg.getValue())) pstmt.setNull(index, arg.getSqlType());
		else pstmt.setObject(index, arg.getValue(), arg.getSqlType());
	}
}