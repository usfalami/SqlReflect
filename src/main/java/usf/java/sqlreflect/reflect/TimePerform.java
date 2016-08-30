package usf.java.sqlreflect.reflect;

import java.util.ArrayList;
import java.util.List;

public class TimePerform {
	
	private List<ActionPerform> times;
	private int rowCount;
	
	public TimePerform() {
		times = new ArrayList<ActionPerform>();
	}

	public List<ActionPerform> getTimes() {
		return times;
	}

	public void setTimes(List<ActionPerform> times) {
		this.times = times;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	
	public ActionPerform startAction(String name){
		ActionPerform t = new ActionPerform(name);
		times.add(t);
		t.start();
		return t;
	}
	
	
}
