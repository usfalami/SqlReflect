package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.mapper.ColumnMapper;
import usf.java.sql.core.mapper.Mapper;

public class ProcedureScanner implements Scanner {
	
	public <T extends Function> void run(HasScanner<T> adapter, String database, String procedure) throws SQLException {
		adapter.start();
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			run(dm, adapter, database, procedure);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(cnx);
		}
		adapter.end();
	}
	
	public <T extends Function> void run(DatabaseMetaData dm, HasScanner<T> adapter, String database, String procedure) throws SQLException {
		adapter.start();
		ColumnMapper colMapper = new ColumnMapper(); //TODO
		ResultSet procs = null;
		try {
			int row = 0;
			procs = dm.getProcedures(null, database, procedure);
			while(procs.next()){
				T p = adapter.getMapper().map(procs, row+1);
				ResultSet cols = null;
				try {
					cols = dm.getProcedureColumns(null, p.getDatabase(), p.getName(), null);
					p.setColumns(listColumns(cols, colMapper));
					adapter.adapte(p, row++);
				} catch (SQLException e) {
					e.printStackTrace();
					throw e;
				}
				finally {
					adapter.getConnectionManager().close(cols);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(procs);
			adapter.end();
		}
	}
	
	protected Column[] listColumns(ResultSet rs, Mapper<?extends Column> mapper) throws SQLException {
		List<Column> list = new ArrayList<Column>();
		int row = 1;
		while(rs.next()) 
			list.add(mapper.map(rs, row++));
		return list.toArray(new Column[list.size()]);
	}
	
}
