package usf.java.sqlreflect.reflect;

import java.util.ArrayList;
import java.util.Collection;

public class ActionTimer {

	private String name;
	private long start, end;
	private Collection<ActionTimer> timers;

	protected ActionTimer() {}
	
	public ActionTimer(String name) {
		this.name = name;
	}

	public long start() {
		return start = end = System.currentTimeMillis();
	}
	public long getStart() {
		return start;
	}
	public long end() {
		return end = System.currentTimeMillis();
	}
	public long getEnd() {
		return end;
	}
	public String getName() {
		return name;
	}
	public ActionTimer setName(String name) {
		this.name = name;
		return this;
	}
	public long duration(){
		return end - start;
	}

	public Collection<ActionTimer> getTimers() {
		return timers;
	}
	
	public ActionTimer createAction(){
		ActionTimer t = new ActionTimer();
		if(timers == null) timers = new ArrayList<ActionTimer>();
		timers.add(t);
		return t;
	}
	public ActionTimer startAction(String name){
		ActionTimer t = createAction();
		t.setName(name).start();
		return t;
	}

	public ActionTimer clear() {
		if(timers != null) timers.clear();
		return this;
	}
	
	@Override
	public String toString() {
		return this.name + " : " + duration();
	}

}
