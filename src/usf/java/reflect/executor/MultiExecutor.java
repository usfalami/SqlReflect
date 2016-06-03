package usf.java.reflect.executor;

import java.sql.SQLException;

import usf.java.adapter.executor.ExecutorAdapter;
import usf.java.reflect.AbstractReflect;

public class MultiExecutor<T extends ExecutorAdapter> extends AbstractReflect<T> {

	public void exec(Executor<?> ... executors) throws SQLException {
		for(int i=0; i<executors.length; i++){
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						//do domething
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			new Thread(t).start();
		}
	}


}
