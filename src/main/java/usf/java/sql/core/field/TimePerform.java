package usf.java.sql.core.field;


public class TimePerform {

	protected long start, cnxStart, statStart, execStart, execEnd, statEnd, cnxEnd, end;
	protected int count;
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getCnxStart() {
		return cnxStart;
	}
	public void setCnxStart(long cnxStart) {
		this.cnxStart = cnxStart;
	}
	public long getStatStart() {
		return statStart;
	}
	public void setStatStart(long statStart) {
		this.statStart = statStart;
	}
	public long getExecStart() {
		return execStart;
	}
	public void setExecStart(long execStart) {
		this.execStart = execStart;
	}
	public long getExecEnd() {
		return execEnd;
	}
	public void setExecEnd(long execEnd) {
		this.execEnd = execEnd;
	}
	public long getStatEnd() {
		return statEnd;
	}
	public void setStatEnd(long statEnd) {
		this.statEnd = statEnd;
	}
	public long getCnxEnd() {
		return cnxEnd;
	}
	public void setCnxEnd(long cnxEnd) {
		this.cnxEnd = cnxEnd;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	
	
}
