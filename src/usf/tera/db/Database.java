package usf.tera.db;

import java.io.Serializable;

import usf.tera.field.SQL;
import usf.tera.reflect.ReflectFactory.Env;

public interface Database {

	String getDriver();
	String makeURL(Env env);
	
	SQL build(String sql, Serializable... parameters);
	
}
