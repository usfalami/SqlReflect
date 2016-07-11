package usf.java.sql.core.reflect.scanner;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.mapper.ColumnMapper;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.Reflector;

public class ResultColumnScanner extends Reflector implements Scanner {
	
	public ResultColumnScanner(ConnectionManager cm) {
		super(cm);
	}

	public void run(ScannerAdapter<Column> adapter, Callable callable, Serializable ... parametters) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = cm.getConnection();
			Statement stmt = null;
			try {
				stmt = cm.buildStatement(cnx, callable.getSQL(), parametters);
				run(stmt, adapter, callable, parametters);
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

	protected void run(Statement stmt, ScannerAdapter<Column> adapter, Callable callable, Serializable ... parametters) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = stmt instanceof Statement ? stmt.executeQuery(callable.getSQL()) : ((PreparedStatement)stmt).executeQuery();
			Mapper<Column> mapper = new ColumnMapper();
			ResultSetMetaData rm = rs.getMetaData();
			adapter.headers(mapper.getColumnNames());
			for(int i=1; i<=rm.getColumnCount(); i++) {
				Column col = mapper.map(rs, i);
				adapter.adapte(col, i);
			}
		} catch (SQLException e) {
			throw e;
		}
		finally {
			cm.close(rs);
			adapter.end();
		}
	}

}
