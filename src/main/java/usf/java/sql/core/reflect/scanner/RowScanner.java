package usf.java.sql.core.reflect.scanner;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.Reflector;
import usf.java.sql.core.reflect.ReflectorUtils;

public class RowScanner extends Reflector implements Scanner {

	public RowScanner(ConnectionManager cm) {
		super(cm);
	}

	public <T> void run(ScannerAdapter<T> adapter, Mapper<T> mapper, Callable callable, Serializable ... parametters) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = cm.getConnection();
			Statement stmt = null;
			try {
				stmt = cm.buildStatement(cnx, callable.getSQL(), parametters);
				run(stmt, adapter, mapper, callable, parametters);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				cm.close(stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(cnx);
		}
	}

	protected <T> void run(Statement stmt, ScannerAdapter<T> adapter, Mapper<T> mapper, Callable callable, Serializable ... parametters) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = stmt instanceof Statement ? stmt.executeQuery(callable.getSQL()) : ((PreparedStatement)stmt).executeQuery();
			if(mapper.getColumnNames() == null) // set all column if no column was set
				mapper.setColumnNames(ReflectorUtils.columnNames(rs));
			adapter.headers(mapper.getColumnNames());
			int row = 0;
			while(rs.next()) {
				T bean = mapper.map(rs, row+1);
				adapter.adapte(bean, row++);
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(rs);
			adapter.end();
		}
	}

}
