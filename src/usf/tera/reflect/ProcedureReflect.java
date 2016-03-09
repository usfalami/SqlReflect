package usf.tera.reflect;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.tera.reflect.field.Parameter;
import usf.tera.reflect.field.Procedure;

public class ProcedureReflect extends Reflect {
	
	@Override
	protected void find(DatabaseMetaData dm, String name) throws SQLException {
		ResultSet rs = dm.getProcedures("", env.getSchema(), name);
		try {
			if(!rs.next()) adapter.performProcedure(null);
			else listProcs(rs, dm);
		} catch (Exception e) {
			adapter.onException(e);
		}
		finally {
			if(rs != null) rs.close();
		}
	}
	
	protected void listProcs(ResultSet rs, DatabaseMetaData dm) throws SQLException, IOException {
		ResultSet param = null;
		try {
			do {
				String name = rs.getString("PROCEDURE_NAME");
				List<Parameter> list = new ArrayList<Parameter>();
				param = dm.getProcedureColumns("", env.getSchema(), name, "");
				list = listColumns(param);
				adapter.performProcedure(new Procedure(name, env.getSchema(), list.toArray(new Parameter[list.size()])));
			}while(rs.next());
		} catch (Exception e) {
			adapter.onException(e);
		}
		finally {
			if(param != null) param.close();
		}
	}
	
	protected List<Parameter> listColumns(ResultSet rs) throws SQLException {
		int cp=0;
		List<Parameter> list = new ArrayList<Parameter>();
		while(rs.next()) { //2->schema; 3->name
			list.add(new Parameter(++cp,
				rs.getString("COLUMN_NAME").toString(),
				rs.getString("TYPE_NAME").toString(),
				rs.getInt("LENGTH")
			));
		}
		return list;
	}
}
