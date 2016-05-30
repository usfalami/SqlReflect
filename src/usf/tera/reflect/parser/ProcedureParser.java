package usf.tera.reflect.parser;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.tera.adpter.parser.ParserAdapter;
import usf.tera.field.Parameter;
import usf.tera.field.Procedure;

public class ProcedureParser<T extends ParserAdapter> extends AbstractParser<T> {
	
	@Override
	protected void lookup(DatabaseMetaData dm, String name) throws SQLException {
		ResultSet rs = dm.getProcedures("", rf.getEnv().getSchema(), name);
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
	
	protected void listProcs(ResultSet rs, DatabaseMetaData dm) throws SQLException, IOException { int cp=0;
		do {
			String name = rs.getString("PROCEDURE_NAME");
			adapter.performProcedureStart(name);
			List<Parameter> list = new ArrayList<Parameter>();
			ResultSet param = null;
			try {
				param = dm.getProcedureColumns("", rf.getEnv().getSchema(), name, "");
				list = listColumns(param);
				adapter.performProcedure(new Procedure(name, rf.getEnv().getSchema(), list.toArray(new Parameter[list.size()])));
			} catch (Exception e) {
				adapter.onException(e);
			}
			finally {
				if(param != null) param.close();
			}
		}while(rs.next());
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
