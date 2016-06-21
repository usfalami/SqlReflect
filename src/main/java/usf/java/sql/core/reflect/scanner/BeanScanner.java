package usf.java.sql.core.reflect.scanner;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.reflect.Reflector;

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
				adapter.start();
				ResultSet rs = null;
				try {
					rs = stmt instanceof Statement ? 
							stmt.executeQuery(callable.getSQL()) : ((PreparedStatement)stmt).executeQuery();
					int row = 0;
					while(rs.next()) {
						T bean = adapter.getMapper().map(rs, row+1);
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

}
