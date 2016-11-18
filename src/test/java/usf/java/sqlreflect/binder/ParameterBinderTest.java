package usf.java.sqlreflect.binder;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import usf.java.sqlreflect.ContextLoader;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.sql.ParameterFactory;
import usf.java.sqlreflect.sql.Parameters;

public class ParameterBinderTest {
	
	@Test
	public void testExecPreparedStatment() {
		Parameters p = new Parameters(
				ParameterFactory.VARCHAR_WRAPPER("MAR"),
				ParameterFactory.VARCHAR_WRAPPER("Fès-Boulemane"));
		ParameterBinder pb = new ParameterBinder();
		ConnectionManager cm = ContextLoader.getConnectionManager();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(Queries.query1, p, pb);
			assertTrue(stmt instanceof PreparedStatement);
			rs = cm.executeQuery(stmt, Queries.query1, p, pb);
			assertTrue(rs.next());
			assertEquals(rs.getString("Name"), "Fès");
			cm.close(rs);
			assertTrue(rs.isClosed());
			assertFalse(cm.isClosed());
			cm.close(stmt);
			assertTrue(stmt.isClosed());
			assertFalse(cm.isClosed());
			cm.close();
			assertTrue(cm.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExecPreparedStatment2() {
		Parameters p = new Parameters(
				ParameterFactory.INTEGER_WRAPPER(2486));
		ParameterBinder pb = new ParameterBinder();
		ConnectionManager cm = ContextLoader.getConnectionManager();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(Queries.query2, p, pb);
			assertTrue(stmt instanceof PreparedStatement);
			rs = cm.executeQuery(stmt, Queries.query1, p, pb);
			assertTrue(rs.next());
			assertEquals(rs.getString("Name"), "Morocco");
			cm.close(rs);
			assertTrue(rs.isClosed());
			assertFalse(cm.isClosed());
			cm.close(stmt);
			assertTrue(stmt.isClosed());
			assertFalse(cm.isClosed());
			cm.close();
			assertTrue(cm.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testExecPreparedStatment3() {
		Parameters p = new Parameters(
				ParameterFactory.CHAR_WRAPPER("MAR"));
		ParameterBinder pb = new ParameterBinder();
		ConnectionManager cm = ContextLoader.getConnectionManager();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(Queries.query3, p, pb);
			assertTrue(stmt instanceof PreparedStatement);
			rs = cm.executeQuery(stmt, Queries.query1, p, pb);
			assertTrue(rs.next());
			assertEquals(rs.getString("Name"), "Morocco");
			cm.close(rs);
			assertTrue(rs.isClosed());
			assertFalse(cm.isClosed());
			cm.close(stmt);
			assertTrue(stmt.isClosed());
			assertFalse(cm.isClosed());
			cm.close();
			assertTrue(cm.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
