package usf.java.db;

import java.io.Serializable;

import usf.java.field.SQL;
import usf.java.reflect.ReflectFactory.Env;

public interface Database {

	String getDriver();
	String makeURL(Env env);
	
	SQL build(String sql, Serializable... parameters);
	
}
