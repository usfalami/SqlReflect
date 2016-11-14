package usf.java.sqlreflect.binder;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import usf.java.sqlreflect.AbstractTest;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.sql.ParameterFactory;
import usf.java.sqlreflect.sql.Parameters;

public class ParameterBinderTest {

	private Statement stmt = null;
	private ResultSet rs = null;
	
	@Test
	public void testExecPreparedStatment() {
		Parameters p = new Parameters(
				ParameterFactory.VARCHAR_WRAPPER("MAR"),
				ParameterFactory.VARCHAR_WRAPPER("Fès-Boulemane"));
		ParameterBinder pb = new ParameterBinder();
		try {
			assertTrue(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().openConnection();
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			stmt = AbstractTest.getConnectionManager().prepare(Queries.query1, p, pb);
			assertTrue(stmt instanceof PreparedStatement);
			rs = AbstractTest.getConnectionManager().executeQuery(stmt, Queries.query1, p, pb);
			assertTrue(rs.next());
			assertEquals(rs.getString("Name"), "Fès");
			AbstractTest.getConnectionManager().close(rs);
			assertTrue(rs.isClosed());
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().close(stmt);
			assertTrue(stmt.isClosed());
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().close();
			assertTrue(AbstractTest.getConnectionManager().isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
