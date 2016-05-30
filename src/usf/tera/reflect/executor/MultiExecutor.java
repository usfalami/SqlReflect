package usf.tera.reflect.executor;

import java.sql.SQLException;

import usf.tera.adapter.executor.ExecutorAdapter;

public class MultiExecutor<T extends ExecutorAdapter> extends AbstractExcecutor<T> {

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
