package usf.java.sql.db.type;

import java.util.Arrays;

import usf.java.sql.db.Database;
import usf.java.sql.db.Env;
import usf.java.sql.db.field.Procedure;
import junit.framework.TestCase;

public class TeradataTest extends TestCase {
	
	public void testGetDriver() {
		Database db = new Teradata();
		assertNotNull(db.getDriver());
	}
	
	public void testMakeURL() {
		Database db = new Teradata();
		String url = db.makeURL(new Env("localhost", "sc_1", 6655));
		String exp = "jdbc:teradata://localhost/database=sc_1,dbs_port=6655,tmode=tera,charset=utf8,";
		assertEquals(exp, url);
	}
	
	public void testParseProcedure() {
		Database db = new Teradata();
		assertNull(db.parseProcedure("select database"));
		assertNull(db.parseProcedure("exec test_macro(?,?,?)"));
		String query = "call sc_1.test_proc(?,?,?,'param')";
		Procedure p = db.parseProcedure(query);
		assertNotNull(p);
		assertEquals(p.getName(), "test_proc");
		assertEquals(p.getSchema(), "sc_1");
		assertEquals(p.getQuery(), query);
		assertTrue(Arrays.equals(p.getParameters(), new String[]{"?","?","?","'param'"}));
	}
	
}