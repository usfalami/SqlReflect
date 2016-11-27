package usf.java.sqlreflect;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

import java.lang.reflect.Method;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.junit.Test;

import usf.java.sqlreflect.binder.ParameterBinder;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.server.User;
import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.Parameters;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Table;

public class UtilsTest {
	
	@Test
	public void testIsNull() {
		assertTrue(Utils.isNull(null));
		String s = null;
		assertTrue(Utils.isNull(s));
		assertFalse(Utils.isNull(""));
		assertFalse(Utils.isNull(0));
		s="";
		assertFalse(Utils.isNull(s));
	}
	@Test
	public void testIsNotNull() {
		assertFalse(Utils.isNotNull(null));
		String s = null;
		assertFalse(Utils.isNotNull(s));
		assertTrue(Utils.isNotNull(""));
		assertTrue(Utils.isNotNull(0));
		s="";
		assertTrue(Utils.isNotNull(s));
	}

	@Test
	public void testIsEmptyString() {
		assertTrue(Utils.isEmptyString(null));
		assertTrue(Utils.isEmptyString(""));
		assertFalse(Utils.isEmptyString("value"));
	}
	@Test
	public void testIsEmptyArray() {
		assertTrue(Utils.isEmptyArray(null));
		assertTrue(Utils.isEmptyArray(new Object[]{}));
		assertTrue(Utils.isEmptyArray(new String[]{}));
		assertFalse(Utils.isEmptyArray(new String[]{""}));
		String s = null;
		assertFalse(Utils.isEmptyArray(new String[]{s}));
		assertFalse(Utils.isEmptyArray(new String[]{"value", "value2"}));
	}
	
	@Test
	public void testIsEmptyPrimitiveArray() {
		assertTrue(Utils.isEmptyPrimitiveArray(null));
		int[] arr = null;
		assertTrue(Utils.isEmptyPrimitiveArray(arr));
		assertTrue(Utils.isEmptyPrimitiveArray(new int[]{}));
		assertFalse(Utils.isEmptyPrimitiveArray(new int[5]));
		assertFalse(Utils.isEmptyPrimitiveArray(new int[]{0}));
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
	public void testIsIllegalArg() {
		List<Parameter<?>> args = null;
		assertFalse(Utils.isIllegalArg(args, null));
		assertTrue(Utils.isIllegalArg(args, new ParameterBinder()));
		args = new ArrayList<Parameter<?>>();
		assertTrue(Utils.isIllegalArg(args, null));
		assertFalse(Utils.isIllegalArg(args, new ParameterBinder()));
		args.add(new Parameter<String>(Types.CHAR, "Test"));
		assertTrue(Utils.isIllegalArg(args, null));
		assertFalse(Utils.isIllegalArg(args, new ParameterBinder()));
	}

	@Test
	public void testIsIllegalArgs() {
		List<List<Parameter<?>>> args = null;
		assertFalse(Utils.isIllegalArgs(args, null));
		assertTrue(Utils.isIllegalArgs(args, new ParameterBinder()));
		args = new ArrayList<List<Parameter<?>>>();
		assertFalse(Utils.isIllegalArgs(args, null));
		assertTrue(Utils.isIllegalArgs(args, new ParameterBinder()));
		args.add(new Parameters());
		assertTrue(Utils.isIllegalArgs(args, null));
		assertFalse(Utils.isIllegalArgs(args, new ParameterBinder()));
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
		testFindMethodTrue(new Integer(0), "intValue");//find method without args
		testFindMethodTrue(new Integer(9), "compareTo", new Integer(5));//String=String
		//		testTrueFindMethod("", "substring", 1, 2); //TODO check primitive variables
		testFindMethodTrue("", "concat", "");
		testFindMethodTrue("", "equals", new Double(5)); //Double->Object
		testFindMethodTrue(new ArrayList<Entry>(), "add", new Table());//Table->Entry
	}
	@Test(expected=NoSuchMethodError.class)
	public void testFindMethod2() throws Exception {
		Utils.findMethod(new Integer(0), "intValue", "");
	}
	@Test(expected=NoSuchMethodError.class)
	public void testFindMethod3() throws Exception {
		Utils.findMethod("", "concat", 99);//Integer # String
	}

	private void testFindMethodTrue(Object o, String methodName, Object... args) throws Exception{
		Method m = Utils.findMethod(o, methodName, args);
		assertEquals(m.getName(), methodName);
		assertEquals(m.getParameterTypes().length, args.length);
		for(int i=0; i<args.length; i++)
			assertTrue(m.getParameterTypes()[i].isInstance(args[i]));
	}

}
