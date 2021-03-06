package usf.java.sqlreflect.binder;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import org.junit.Test;

import usf.java.sqlreflect.sql.entry.GenericType;

public class BinderProxyTest {

	@Test(expected=IllegalArgumentException.class)
	public void testGet1_1() throws Exception {
		String className = null;
		BinderProxy.get(className, null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet1_2() throws Exception {
		String className = null;
		BinderProxy.get(className, "");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet1_3() throws Exception {
		BinderProxy.get("", null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet1_4() throws Exception {
		BinderProxy.get("", "");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet1_5() throws Exception {
		BinderProxy.get(MultipleBinder.class.getName(), null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet1_6() throws Exception {
		BinderProxy.get(MultipleBinder.class.getName(), "");
	}
	@Test(expected=ClassCastException.class)
	public void testGet1_7() throws Exception {
		BinderProxy.get(this.getClass().getName(), "concat");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGet2_1() throws Exception {
		Class<MultipleBinder<GenericType>> className = null;
		BinderProxy.get(className, null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet2_2() throws Exception {
		Class<MultipleBinder<GenericType>> className = null;
		BinderProxy.get(className, "");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet2_3() throws Exception {
		BinderProxy.get(GenericTypeMultiBinder.class, null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet2_4() throws Exception {
		BinderProxy.get(GenericTypeMultiBinder.class, "");
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGet3_1() throws Exception {
		MultipleBinder<GenericType> obj = null;
		BinderProxy.get(obj, null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet3_2() throws Exception {
		MultipleBinder<GenericType> obj = null;
		BinderProxy.get(obj, "");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet3_3() throws Exception {
		MultipleBinder<GenericType> obj = new GenericTypeMultiBinder();
		BinderProxy.get(obj, null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testGet3_4() throws Exception {
		MultipleBinder<GenericType> obj = new GenericTypeMultiBinder();
		BinderProxy.get(obj, "");
	}
	
	@Test(expected=ClassNotFoundException.class)
	public void testGet4() throws Exception {
		BinderProxy.get("a.b.c.MyBinder.class", "notExistingMethod");
	}
	@Test
	public void testGet5() throws Exception {
		String method = "findCityByCountryAndDistrict";
		BinderProxy<GenericType> b1 =  BinderProxy.get(GenericTypeMultiBinder.class.getName(), method);
		assertNotNull(b1.getBinder());
		assertNotNull(b1.getBinderMethodName());
		BinderProxy<GenericType> b2 =  BinderProxy.get(GenericTypeMultiBinder.class, method);
		assertNotNull(b2.getBinder());
		assertNotNull(b2.getBinderMethodName());
		BinderProxy<GenericType> b3 =  BinderProxy.get(new GenericTypeMultiBinder(), method);
		assertNotNull(b3.getBinder());
		assertNotNull(b3.getBinderMethodName());
		assertEquals(b1.getBinderMethodName(), method);
		assertEquals(b1.getBinderMethodName(), b2.getBinderMethodName());
		assertEquals(b1.getBinderMethodName(), b3.getBinderMethodName());
		assertEquals(b1.getBinder().getClass(), GenericTypeMultiBinder.class);
		assertEquals(b1.getBinder().getClass(), b2.getBinder().getClass());
		assertEquals(b1.getBinder().getClass(), b3.getBinder().getClass());
	}

}
