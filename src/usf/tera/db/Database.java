package usf.tera.db;

import usf.tera.reflect.ReflectFactory.Env;

public interface Database {

	String getDriver();
	String makeURL(Env env);
	
}
