package usf.java.sql.db.type;

import java.util.Arrays;

import usf.java.sql.db.Env;
import usf.java.sql.db.Server;
import usf.java.sql.db.field.Macro;
import usf.java.sql.db.field.Procedure;
import usf.java.sql.db.server.TeradataServer;
import junit.framework.TestCase;

public class TeradataTest extends TestCase {
	
	public void testGetDriver() {
		Server db = new TeradataServer();
		assertNotNull(db.getDriver());
	}
	
	public void testMakeURL() {
		Server db = new TeradataServer();
		String url = db.makeURL(new Env("localhost", "db_1", 6655));
		String exp = "jdbc:teradata://localhost/database=db_1,dbs_port=6655,";
		assertEquals(exp, url);
		url = db.makeURL(new Env("127.0.0.1", "db_2", 7001, "tmode=tera,charset=utf8"));
		exp = "jdbc:teradata://127.0.0.1/database=db_2,dbs_port=7001,tmode=tera,charset=utf8";
		assertEquals(exp, url);
	}
	
	public void testParseProcedure() {
		Server db = new TeradataServer();
		assertNull(db.parseProcedure("select database"));
		assertNull(db.parseProcedure("exec test_macro(?,?,?)"));
		String query = "call sc_1.test_proc(?,?,?,'param')";
		Procedure p = db.parseProcedure(query);
		assertNotNull(p);
		assertEquals(p.getName(), "test_proc");
		assertEquals(p.getDatabase(), "sc_1");
		assertEquals(p.getQuery(), query);
		assertTrue(Arrays.equals(p.getParameters(), new String[]{"?","?","?","'param'"}));
	}
	
	public void testParseMacro() {
		Server db = new TeradataServer();
		assertNull(db.parseMacro("select database"));
		assertNull(db.parseMacro("call sc_1.test_proc(?,?,?)"));
		String query = "exec sc_1.test_macro(?,?,?,'param')";
		Macro p = db.parseMacro(query);
		assertNotNull(p);
		assertEquals(p.getName(), "test_macro");
		assertEquals(p.getDatabase(), "sc_1");
		assertEquals(p.getQuery(), query);
		assertTrue(Arrays.equals(p.getParameters(), new String[]{"?","?","?","'param'"}));
	}
	
}