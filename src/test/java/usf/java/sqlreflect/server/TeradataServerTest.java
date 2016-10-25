package usf.java.sqlreflect.server;

import java.util.Arrays;

import junit.framework.TestCase;
import usf.java.sqlreflect.sql.entry.item.Callable;
import usf.java.sqlreflect.sql.entry.item.Procedure;


public class TeradataServerTest extends TestCase {
	
	public void testGetDriver() {
		Server db = new TeradataServer();
		assertNotNull(db.getDriver());
	}
	
	public void testMakeURL() {
		Server db = new TeradataServer();
		String url = db.getURL(new Env("localhost", "db_1", 6655));
		String exp = "jdbc:teradata://localhost/database=db_1,dbs_port=6655,";
		assertEquals(exp, url);
		url = db.getURL(new Env("127.0.0.1", "db_2", 7001, "tmode=tera,charset=utf8"));
		exp = "jdbc:teradata://127.0.0.1/database=db_2,dbs_port=7001,tmode=tera,charset=utf8";
		assertEquals(exp, url);
	}
	
	public void testParseProcedure() {
		Server db = new TeradataServer();
		assertNull(db.parseCallable("select database"));
		assertNull(db.parseCallable("cal bd_1.test_proc(?,?,?)"));
		assertNull(db.parseCallable("call test_proc(?,?,?)"));
		
		String query = "call bd_1.test_proc(?,?,?,'param')";
		Callable p = db.parseCallable(query);
		assertNotNull(p);
		assertEquals(p.getClass(), Procedure.class);
		assertEquals(p.getName(), "test_proc");
		assertEquals(p.getDatabaseName(), "bd_1");
		assertEquals(p.asQuery(), query);
		assertTrue(Arrays.equals(p.getParameters(), new String[]{"?","?","?","'param'"}));
	}
	
//	public void testParseMacro() {
//		Server db = new TeradataServer();
//		assertNull(db.parseCallable("select database"));
//		assertNull(db.parseCallable("exc bd_1.test_macro(?,?,?)"));
//		assertNull(db.parseCallable("call test_macro(?,?,?)"));
//		
//		String query = "exec bd_1.test_macro(1223, true, ?,'param')";
//		Callable p = db.parseCallable(query);
//		assertNotNull(p);
//		assertEquals(p.getClass(), Macro.class);
//		assertEquals(p.getName(), "test_macro");
//		assertEquals(p.getDatabaseName(), "bd_1");
//		assertEquals(p.asQuery(), query);
//		assertTrue(Arrays.equals(p.getParameters(), new String[]{"1223","true","?","'param'"}));
//	}
	
}