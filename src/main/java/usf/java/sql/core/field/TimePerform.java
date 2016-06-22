package usf.java.sql.core.field;

import java.util.Date;

public class TimePerform {

	protected long start, cnxStart, statStart, execStart, execEnd, statEnd, cnxEnd, end;
	protected int count;

	public Date getCnxStart() {
		return new Date(cnxStart);
	}
	public void setCnxStart(long cnxStart) {
		this.cnxStart = cnxStart;
	}
	public Date getStatStart() {
		return new Date(statStart);
	}
	public void setStatStart(long statStart) {
		this.statStart = statStart;
	}
	public Date getExecStart() {
		return new Date(execStart);
	}
	public void setExecStart(long execStart) {
		this.execStart = execStart;
	}
	public Date getExecEnd() {
		return new Date(execEnd);
	}
	public void setExecEnd(long execEnd) {
		this.execEnd = execEnd;
	}
	public Date getStatEnd() {
		return new Date(statEnd);
	}
	public void setStatEnd(long statEnd) {
		this.statEnd = statEnd;
	}
	public Date getCnxEnd() {
		return new Date(cnxEnd);
	}
	public void setCnxEnd(long cnxEnd) {
		this.cnxEnd = cnxEnd;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
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
