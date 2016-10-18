package usf.java.sqlreflect.reflect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TimePerform {
	
	private Collection<ActionTimer> times;
	private int rowCount;
	
	public TimePerform() {
		times = new ArrayList<ActionTimer>();
	}

	public Collection<ActionTimer> getTimes() {
		return times;
	}

	public void setTimes(List<ActionTimer> times) {
		this.times = times;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public ActionTimer startAction(String name){
		ActionTimer t = new ActionTimer(name);
		times.add(t);
		t.start();
		return t;
	}
	
	public TimePerform clear() {
		times.clear();
		rowCount = 0;
		return this;
	}
	
}
