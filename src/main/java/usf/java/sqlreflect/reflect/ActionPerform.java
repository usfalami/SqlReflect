package usf.java.sqlreflect.reflect;

public class ActionPerform {

	private long start, end;
	private String name;

	protected ActionPerform() {
		// TODO Auto-generated constructor stub
	}
	
	public ActionPerform(String name) {
		this.name = name;
	}

	public long start() {
		return start = System.currentTimeMillis();
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
	public ActionPerform setName(String name) {
		this.name = name;
		return this;
	}
	public long duration(){
		return end - start;
	}

}
