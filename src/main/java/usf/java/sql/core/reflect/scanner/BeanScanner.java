package usf.java.sql.core.reflect.scanner;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.mapper.DynamicMapper;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.Reflector;
import usf.java.sql.core.reflect.Utils;

public class BeanScanner extends Reflector implements Scanner {

	public BeanScanner(ConnectionManager cm) {
		super(cm);
	}

	public <T> void run(HasScanner<T> adapter, Callable callable, Serializable ... parametters) throws SQLException {
		Connection cnx = null;
		try {
			cnx = cm.newConnection();
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

	protected <T> void run(Statement stmt, HasScanner<T> adapter, Callable callable, Serializable ... parametters) throws SQLException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = stmt instanceof Statement ? stmt.executeQuery(callable.getSQL()) : ((PreparedStatement)stmt).executeQuery();
			Mapper<T> mapper = adapter.getMapper();
			if(mapper != null) {
				if(mapper instanceof DynamicMapper){
					DynamicMapper<T> dm = (DynamicMapper<T>)mapper;
					if(dm.getColumnNames() == null)
						dm.setColumnNames(Utils.columnNames(rs));
				}
				int row = 0;
				while(rs.next()) {
					T bean = mapper.map(rs, row+1);
					adapter.adapte(bean, row++);
				}
				rs.beforeFirst();
			}
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
