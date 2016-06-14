package fr.stm;

import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import usf.java.sql.adapter.reflect.executor.ExecutorMapper;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.connection.SimpleConnectionManager;
import usf.java.sql.core.db.Env;
import usf.java.sql.core.db.Server;
import usf.java.sql.core.db.User;
import usf.java.sql.core.db.server.TeradataServer;
import usf.java.sql.core.mapper.DefaultBeanMapper;

public class ExecutorMapperTest extends TestCase {

	private static Server db = new TeradataServer();
	private static Env env_ra4 = new Env("BDD_STM_DEV", "STM_IHM_RA4", 1025, "tmode=tera,charset=utf8");
	private static User user_ra4 = new User("stm_dba_ra4", "stm_dba_ra4");
	private static ConnectionManager cm = new SimpleConnectionManager(db, env_ra4, user_ra4);
	
	public void test01() throws Exception {
		DefaultBeanMapper mapper = new DefaultBeanMapper(new String[]{"1"});
		ExecutorMapper<Map<String, Object>> exec = new ExecutorMapper<Map<String,Object>>(cm, mapper);
		exec.execute("SELECT 1");
		List<Map<String, Object>> map = exec.getBeans();
		assertNotNull(map);
		assertEquals(map.size(), 1);
		assertEquals(map.get(0).get("1"), 1);
	}

}
