package usf.java.sql.core.db.type;

import java.util.Arrays;

import junit.framework.TestCase;
import usf.java.sql.core.db.Env;
import usf.java.sql.core.db.Server;
import usf.java.sql.core.db.field.SQL;
import usf.java.sql.core.db.server.TeradataServer;


public class TeradataServerTest extends TestCase {
	
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
	
	public void testParseFuncion() {
		Server db = new TeradataServer();
		assertNull(db.parseFunction("select database"));
		assertNull(db.parseFunction("exc sc_1.test_macro(?,?,?)"));
		assertNull(db.parseFunction("cal sc_1.test_proc(?,?,?)"));
		
		String query = "call sc_1.test_proc(?,?,?,'param')";
		SQL p = db.parseFunction(query);
		assertNotNull(p);
		assertEquals(p.getName(), "test_proc");
		assertEquals(p.getDatabase(), "sc_1");
		assertEquals(p.get(), query);
		assertTrue(Arrays.equals(p.getParameters(), new String[]{"?","?","?","'param'"}));

		query = "exec sc_1.test_macro(1223, true, ?,'param')";
		p = db.parseFunction(query);
		assertNotNull(p);
		assertEquals(p.getName(), "test_macro");
		assertEquals(p.getDatabase(), "sc_1");
		assertEquals(p.get(), query);
		assertTrue(Arrays.equals(p.getParameters(), new String[]{"1223","true","?","'param'"}));
	}
	
}