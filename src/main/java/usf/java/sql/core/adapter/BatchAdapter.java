package usf.java.sql.core.adapter;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Reflector.Adapter;

public interface BatchAdapter extends Adapter {

	void update(int[] count) throws AdapterException;

}
