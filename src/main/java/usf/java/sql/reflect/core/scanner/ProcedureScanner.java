package usf.java.sql.reflect.core.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Callable;

public class ProcedureScanner implements Scanner<Scanner.HasFunctionAdapter> {
	
	public void run(Scanner.HasFunctionAdapter adapter, String database, String procedure) throws SQLException{
		adapter.start();
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet procs = null;
			try {
				procs = dm.getProcedures(null, database, procedure);
				while(procs.next()){
					Callable p = new Callable(
							procs.getString("PROCEDURE_SCHEM"), 
							procs.getString("PROCEDURE_NAME"));
							//procs.getString("PROCEDURE_TYPE")
					ResultSet cols = null;
					try {
						cols = dm.getProcedureColumns(null, p.getDatabase(), p.getName(), null);
						Column[] list = listColumns(cols);
						adapter.adapte(p, list);
					} catch (SQLException e) {
						e.printStackTrace();
						throw e;
					}
					finally {
						if(cols != null) cols.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				if(procs != null) procs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().closeConnection(cnx);
		}
		adapter.finish();
	}
	
	protected Column[] listColumns(ResultSet rs) throws SQLException {
		List<Column> list = new ArrayList<Column>();
		while(rs.next()) { //2->database; 3->name
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
