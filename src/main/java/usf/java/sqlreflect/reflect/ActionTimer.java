package usf.java.sqlreflect.reflect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import usf.java.sqlreflect.Utils;

public class ActionTimer {

	private String name;
	private long start, end;
	private Collection<ActionTimer> timers;
	private String msg;

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
	public String getMessage() {
		return msg;
	}
	public ActionTimer setMessage(String msg) {
		this.msg = msg;
		return this;
	}
	
	public ActionTimer last(){
		ActionTimer at = this;
		if(Utils.isEmptyCollection(timers)) return at;
		Iterator<ActionTimer> it = timers.iterator();
		while(it.hasNext()) at = it.next();
		return at.last();
	}
	
	public long duration(){
		return end - start;
	}

	public Collection<ActionTimer> getTimers() {
		return timers;
	}
	
	public ActionTimer createAction(){
		ActionTimer t = new ActionTimer();
		if(Utils.isNull(timers)) timers = new ArrayList<ActionTimer>();
		timers.add(t);
		return t;
	}
	public ActionTimer startAction(String name){
		ActionTimer t = createAction();
		t.setName(name).start();
		return t;
	}

	public ActionTimer clear() {
		if(Utils.isNotNull(timers)) timers.clear();
		return this;
	}
	
	@Override
	public String toString() {
		return this.name + " : " + duration();
	}

}
