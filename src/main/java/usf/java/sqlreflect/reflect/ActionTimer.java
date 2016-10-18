package usf.java.sqlreflect.reflect;

public class ActionTimer {

	private String name;
	private long start, end;

	protected ActionTimer() {
		// TODO Auto-generated constructor stub
	}
	
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

}
