package usf.java.sqlreflect;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.junit.Test;

import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.server.User;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Table;

public class UtilsTest {

	@Test
	public void testIsEmptyString() {
		assertTrue(Utils.isEmptyString(null));
		assertTrue(Utils.isEmptyString(""));
		assertFalse(Utils.isEmptyString("value"));
	}
	@Test
	public void testIsEmptyArray() {
		assertTrue(Utils.isEmptyArray());
		assertTrue(Utils.isEmptyArray(new Object[]{}));
		assertTrue(Utils.isEmptyArray(new String[]{}));
		assertFalse(Utils.isEmptyArray((Object) new String[]{}));
		assertFalse(Utils.isEmptyArray(0));
		assertFalse(Utils.isEmptyArray(""));
		String s = null;
		assertFalse(Utils.isEmptyArray(s));
		assertFalse(Utils.isEmptyArray("value", "value2"));
	}
	@Test
	public void testIsEmptyCollection() {
		assertTrue(Utils.isEmptyCollection(null));
		Collection<Object> l = null;
		assertTrue(Utils.isEmptyCollection(l));
		l = new ArrayList<Object>();
		assertTrue(Utils.isEmptyCollection(l));
		l.add(0);
		assertFalse(Utils.isEmptyCollection(l));
		l = new HashSet<Object>();
		assertTrue(Utils.isEmptyCollection(l));
		l.add("");
		assertFalse(Utils.isEmptyCollection(l));
		l = new Vector<Object>();
		assertTrue(Utils.isEmptyCollection(l));
		l.add(null);
		assertFalse(Utils.isEmptyCollection(l));
	}
	@Test
	public void testIsEmptyMap() {
		assertTrue(Utils.isEmptyMap(null));
		Map<Object, Object> map = null;
		assertTrue(Utils.isEmptyMap(map));
		map = new HashMap<Object, Object>();
		assertTrue(Utils.isEmptyMap(map));
		map.put(0, null);
		assertFalse(Utils.isEmptyMap(map));
		map = new LinkedHashMap<Object, Object>();
		assertTrue(Utils.isEmptyMap(map));
		map.put("", 0);
		assertFalse(Utils.isEmptyMap(map));
	}
	@Test
	public void testIsEmptyUser() {
		assertTrue(Utils.isEmptyUser(null));
		assertTrue(Utils.isEmptyUser(new User(null, null)));
		assertTrue(Utils.isEmptyUser(new User(null, "pass")));
		assertTrue(Utils.isEmptyUser(new User("", null)));
		assertFalse(Utils.isEmptyUser(new User("root", null)));
		Properties p = new Properties();
		assertTrue(Utils.isEmptyUser(new User(p)));
		p.put(Constants.USER_PASSWORD, "pass");
		assertTrue(Utils.isEmptyUser(new User(p)));
		p.put(Constants.USER_LOGIN, "root");
		assertFalse(Utils.isEmptyUser(new User(p)));
	}
	
	@Test
	public void testConvert() {
		assertNull(Utils.convert(null));
		assertEquals(Utils.convert().length, 0);
		assertEquals(Utils.convert(0).length, 1);
		int[] arr = new int[]{1,2,3};
		Integer[] res = Utils.convert(arr);
		assertEquals(arr.length, res.length);
		for(int i=0; i<arr.length; i++)
			assertEquals(arr[i], res[i].intValue());
	}

	@Test
	public void testFindMethod() throws Exception {
		testTrueFindMethod(new Integer(0), "intValue");
		testTrueFindMethod(new Integer(9), "compareTo", new Integer(5));
//		testTrueFindMethod("", "substring", 1, 2); //TODO check primitive variables
		testTrueFindMethod("", "equals", new Double(5));
		testTrueFindMethod("", "contentEquals", "");
		testTrueFindMethod(new ArrayList<Entry>(), "add", new Table());//Table->Entry
	}
	private void testTrueFindMethod(Object o, String methodName, Object... args) throws Exception{
		Method m = Utils.findMethod(o, methodName, args);
		assertEquals(m.getName(), methodName);
		assertEquals(m.getParameterTypes().length, args.length);
		for(int i=0; i<args.length; i++)
			assertTrue(m.getParameterTypes()[i].isInstance(args[i]));
	}
	
}
