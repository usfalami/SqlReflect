package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.exception.AdapterException;

public interface ExecutorAdapter extends Adapter {

	void adapte(int... count) throws AdapterException;

}
