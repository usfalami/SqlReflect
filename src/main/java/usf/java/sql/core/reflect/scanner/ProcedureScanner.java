package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.java.sql.core.db.field.Callable;
import usf.java.sql.core.db.field.Column;
import usf.java.sql.core.mapper.BeanMapper;
import usf.java.sql.core.mapper.ColumnMapper;

public class ProcedureScanner implements Scanner {
	
	public void run(HasCallableScanner adapter, BeanMapper<?extends Callable> mapper, String database, String procedure) throws SQLException {
		adapter.start();
		Connection cnx = null;
		ColumnMapper colMapper = new ColumnMapper(); //TODO
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet procs = null;
			try {
				int row = 1;
				procs = dm.getProcedures(null, database, procedure);
				while(procs.next()){
					Callable p = mapper.map(procs, row++);
					ResultSet cols = null;
					try {
						cols = dm.getProcedureColumns(null, p.getDatabase(), p.getName(), null);
						Column[] list = listColumns(cols, colMapper);
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
	
	protected Column[] listColumns(ResultSet rs, BeanMapper<?extends Column> mapper) throws SQLException {
		List<Column> list = new ArrayList<Column>();
		int row = 1;
		while(rs.next()) 
			list.add(mapper.map(rs, row++));
		return list.toArray(new Column[list.size()]);
	}
	
}
