package usf.java.sqlreflect.binder;

import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import usf.java.sqlreflect.sql.entry.Entry;

public class BinderProxyTest {

	@Test(expected=Exception.class)
	public void testGet1_1() throws Exception {
		String className = null;
		BinderProxy.get(className, null);
	}
	@Test(expected=Exception.class)
	public void testGet1_2() throws Exception {
		String className = null;
		BinderProxy.get(className, "");
	}
	@Test(expected=Exception.class)
	public void testGet1_3() throws Exception {
		BinderProxy.get("", null);
	}
	@Test(expected=Exception.class)
	public void testGet1_4() throws Exception {
		BinderProxy.get("", "");
	}
	@Test(expected=Exception.class)
	public void testGet1_5() throws Exception {
		BinderProxy.get(MultipleBinder.class.getName(), null);
	}
	@Test(expected=Exception.class)
	public void testGet1_6() throws Exception {
		BinderProxy.get(MultipleBinder.class.getName(), "");
	}
	@Test(expected=ClassCastException.class)
	public void testGet1_7() throws Exception {
		BinderProxy.get(this.getClass().getName(), "concat");
	}
	
	@Test(expected=Exception.class)
	public void testGet2_1() throws Exception {
		Class<MultipleBinder<Entry>> className = null;
		BinderProxy.get(className, null);
	}
	@Test(expected=Exception.class)
	public void testGet2_2() throws Exception {
		Class<MultipleBinder<Entry>> className = null;
		BinderProxy.get(className, "");
	}
	@Test(expected=Exception.class)
	public void testGet2_3() throws Exception {
		BinderProxy.get(EntryMultiBinder.class, null);
	}
	@Test(expected=Exception.class)
	public void testGet2_4() throws Exception {
		BinderProxy.get(EntryMultiBinder.class, "");
	}
	
	
	@Test(expected=Exception.class)
	public void testGet3_1() throws Exception {
		MultipleBinder<Entry> obj = null;
		BinderProxy.get(obj, null);
	}
	@Test(expected=Exception.class)
	public void testGet3_2() throws Exception {
		MultipleBinder<Entry> obj = null;
		BinderProxy.get(obj, "");
	}
	@Test(expected=Exception.class)
	public void testGet3_3() throws Exception {
		MultipleBinder<Entry> obj = new EntryMultiBinder();
		BinderProxy.get(obj, null);
	}
	@Test(expected=Exception.class)
	public void testGet3_4() throws Exception {
		MultipleBinder<Entry> obj = new EntryMultiBinder();
		BinderProxy.get(obj, "");
	}
	
	@Test(expected=Exception.class)
	public void testGet4() throws Exception {
		BinderProxy.get("a.b.c.d.class", "notExistingMethod");
	}
	@Test
	public void testGet5() throws Exception {
		String method = "findCityByCountryAndDistrict";
		BinderProxy<Entry> b1 =  BinderProxy.get(EntryMultiBinder.class.getName(), method);
		assertNotNull(b1.getBinder());
		assertNotNull(b1.getBinderMethodName());
		BinderProxy<Entry> b2 =  BinderProxy.get(EntryMultiBinder.class, method);
		assertNotNull(b2.getBinder());
		assertNotNull(b1.getBinderMethodName());
		BinderProxy<Entry> b3 =  BinderProxy.get(new EntryMultiBinder(), method);
		assertNotNull(b3.getBinder());
		assertNotNull(b1.getBinderMethodName());
		assertEquals(b1.getBinderMethodName(), b2.getBinderMethodName());
		assertEquals(b1.getBinderMethodName(), b3.getBinderMethodName());
		assertEquals(b1.getBinder().getClass(), b2.getBinder().getClass());
		assertEquals(b1.getBinder().getClass(), b3.getBinder().getClass());
	}

}
