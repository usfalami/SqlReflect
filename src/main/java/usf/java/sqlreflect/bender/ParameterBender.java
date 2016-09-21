package usf.java.sqlreflect.bender;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.Parameter;

public class ParameterBender implements Binder<Parameter<?>[]> {

	@Override
	public void bind(PreparedStatement pstmt, Parameter<?>[] args) throws SQLException {
		if(args == null) return;
		for(int i=0; i<args.length; i++)
			Utils.set(pstmt, i+1, args[i]);
	}

}
