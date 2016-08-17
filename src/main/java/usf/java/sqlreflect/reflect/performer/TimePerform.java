package usf.java.sqlreflect.reflect.performer;

public class TimePerform {

	protected long start, cnxStart, statStart, execStart, execEnd, mapStart, mapEnd, statEnd, cnxEnd, end;
	protected int rowCount;
	
	public void start() {
		this.start = this.end = System.currentTimeMillis();
	}
	public void cnxStart() {
		this.cnxStart = this.cnxEnd = System.currentTimeMillis();
	}
	public void statStart() {
		this.statStart = this.statEnd = System.currentTimeMillis();
	}
	public void execStart() {
		this.execStart = this.execEnd = System.currentTimeMillis();
	}
	public void mapStart() {
		this.mapStart = this.mapEnd = System.currentTimeMillis();
	}
	public void mapEnd() {
		this.mapEnd = System.currentTimeMillis();
	}
	public void execEnd() {
		this.execEnd = System.currentTimeMillis();
	}
	public void statEnd() {
		this.statEnd = System.currentTimeMillis();
	}
	public void cnxEnd() {
		this.cnxEnd = System.currentTimeMillis();
	}
	public void end() {
		this.end = System.currentTimeMillis();
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
	public long getMapStart() {
		return mapStart;
	}
	public long getMapEnd() {
		return mapEnd;
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
		return mapEnd-mapStart;
	}
}
