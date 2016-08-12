package usf.java.sql.core.adapter;

import usf.java.sql.core.exception.AdapterException;

public interface UpdaterAdapter extends Adapter {

	void adapte(int... count) throws AdapterException;

}
