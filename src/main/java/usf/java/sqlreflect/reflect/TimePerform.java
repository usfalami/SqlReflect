package usf.java.sqlreflect.reflect;

public class TimePerform {

	protected long start, cnxStart, statStart, execStart, execEnd, adaptStart, adaptEnd, statEnd, cnxEnd, end;
	protected int rowCount;
	
	public TimePerform start() {
		this.start = this.end = System.currentTimeMillis();
		return this;
	}
	public TimePerform cnxStart() {
		this.cnxStart = this.cnxEnd = System.currentTimeMillis();
		return this;
	}
	public TimePerform statStart() {
		this.statStart = this.statEnd = System.currentTimeMillis();
		return this;
	}
	public TimePerform execStart() {
		this.execStart = this.execEnd = System.currentTimeMillis();
		return this;
	}
	public TimePerform adaptStart() {
		this.adaptStart = this.adaptEnd = System.currentTimeMillis();
		return this;
	}
	public TimePerform adaptEnd() {
		this.adaptEnd = System.currentTimeMillis();
		return this;
	}
	public TimePerform execEnd() {
		this.execEnd = System.currentTimeMillis();
		return this;
	}
	public TimePerform statEnd() {
		this.statEnd = System.currentTimeMillis();
		return this;
	}
	public TimePerform cnxEnd() {
		this.cnxEnd = System.currentTimeMillis();
		return this;
	}
	public TimePerform end() {
		this.end = System.currentTimeMillis();
		return this;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int count) {
		this.rowCount = count;
	}

	public long getStart() {
		return start;
	}
	public long getEnd() {
		return end;
	}
	public long getCnxStart() {
		return cnxStart;
	}
	public long getCnxEnd() {
		return cnxEnd;
	}
	public long getStatStart() {
		return statStart;
	}
	public long getStatEnd() {
		return statEnd;
	}
	public long getExecStart() {
		return execStart;
	}
	public long getExecEnd() {
		return execEnd;
	}
	public long getAdaptStart() {
		return adaptStart;
	}
	public long getAdaptEnd() {
		return adaptEnd;
	}
	
	public long duration() {
		return end-start;
	}
	public long cnxDuration() {
		return cnxEnd-cnxStart;
	}
	public long statDuration() {
		return statEnd-statStart;
	}
	public long execDuration() {
		return execEnd-execStart;
	}
	public long mapDuration() {
		return adaptEnd-adaptStart;
	}
}
