package usf.java.reflect.parser;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.java.adapter.parser.ParserAdapter;
import usf.java.field.Column;
import usf.java.field.Procedure;

public class ProcedureParser<T extends ParserAdapter> extends AbstractParser<T> {
	
	@Override
	protected void lookup(DatabaseMetaData dm, String name) throws SQLException {
		ResultSet rs = dm.getProcedures("", rf.getEnv().getSchema(), name);
		try {
			if(!rs.next()) adapter.performProcedure(null);
			else listProcs(rs, dm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) rs.close();
		}
	}
	
	protected void listProcs(ResultSet rs, DatabaseMetaData dm) throws SQLException, IOException {
		do {
			String name = rs.getString("PROCEDURE_NAME");
			List<Column> list = new ArrayList<Column>();
			ResultSet param = null;
			try {
				param = dm.getProcedureColumns("", rf.getEnv().getSchema(), name, "");
				list = listColumns(param);
				adapter.performProcedure(new Procedure(rf.getEnv().getSchema(), name), list.toArray(new Column[list.size()]));
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if(param != null) param.close();
			}
		}while(rs.next());
	}
	
	protected List<Column> listColumns(ResultSet rs) throws SQLException {
		List<Column> list = new ArrayList<Column>();
		while(rs.next()) { //2->schema; 3->name
			list.add(new Column(
				rs.getString("COLUMN_NAME").toString(),
				rs.getString("TYPE_NAME").toString(),
				rs.getInt("LENGTH"),
				rs.getInt("COLUMN_TYPE")
			));
		}
		return list;
	}
}
