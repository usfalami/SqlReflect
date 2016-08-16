package usf.java.sqlreflect.core.adapter;

import usf.java.sqlreflect.core.exception.AdapterException;

public interface ExecutorAdapter extends Adapter {

	void adapte(int... count) throws AdapterException;

}
