package usf.java.reflect.parser;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.java.adapter.parser.ParserAdapter;
import usf.java.field.Column;
import usf.java.field.Procedure;
import usf.java.reflect.AbstractReflect;

public class ProcedureParser<T extends ParserAdapter> extends AbstractReflect<T> {

	
	public void list(String schema, String procedure) throws SQLException{
		Connection cnx = null;
		try {
			cnx = rf.newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet procs = null;
			try {
				procs = dm.getProcedures(null, schema, procedure);
				while(procs.next()){
					Procedure p = new Procedure(procs.getString("PROCEDURE_SCHEM"), procs.getString("PROCEDURE_NAME"), procs.getString("PROCEDURE_TYPE"));
					ResultSet cols = null;
					try {
						cols = dm.getProcedureColumns(null, p.getSchema(), p.getName(), null);
						Column[] list = listColumns(cols);
						adapter.performProcedure(p, list);
					} catch (Exception e) {
						e.printStackTrace();
					}
					finally {
						if(cols != null) cols.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if(procs != null) procs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			rf.CloseConnection(cnx);
		}
		
	}
	
	protected Column[] listColumns(ResultSet rs) throws SQLException {
		List<Column> list = new ArrayList<Column>();
		while(rs.next()) { //2->schema; 3->name
			list.add(new Column(
				rs.getString("COLUMN_NAME").toString(),
				rs.getString("TYPE_NAME").toString(),
				rs.getInt("LENGTH"),
				rs.getInt("COLUMN_TYPE")
			));
		}
		return list.toArray(new Column[list.size()]);
	}
	
}
