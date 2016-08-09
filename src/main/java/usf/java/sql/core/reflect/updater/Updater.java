package usf.java.sql.core.reflect.updater;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Worker;
import usf.java.sql.core.reflect.Reflector.Adapter;

public interface Updater extends Worker<Updater.UpdaterAdapter> {
	
	public static interface UpdaterAdapter extends Adapter {

		void adapte(int[] count) throws AdapterException;

	}

}
