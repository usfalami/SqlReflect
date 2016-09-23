package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.executor.BatchExecutor;
import usf.java.sqlreflect.reflect.executor.UpdateExecutor;
import usf.java.sqlreflect.reflect.scanner.ArgumentScanner;
import usf.java.sqlreflect.reflect.scanner.ColumnScanner;
import usf.java.sqlreflect.reflect.scanner.DatabaseScanner;
import usf.java.sqlreflect.reflect.scanner.HeaderScanner;
import usf.java.sqlreflect.reflect.scanner.ProcedureScanner;
import usf.java.sqlreflect.reflect.scanner.RowScanner;
import usf.java.sqlreflect.reflect.scanner.TableScanner;

public class ReflectorFactory {
	
	private TransactionManager tm;
	private TimePerform tp;
	
	public ReflectorFactory(TransactionManager tm, TimePerform tp) {
		this.tm = tm;
		this.tp = tp;
	}

	public DatabaseScanner getDatabaseScanner(){
		return new DatabaseScanner(tm, tp);
	}
	
	public TableScanner getTableScanner(){
		return new TableScanner(tm, tp);
	}
	public ColumnScanner getColumnScanner() {
		return new ColumnScanner(tm, tp);
	}
	
	public ProcedureScanner getProcedureScanner(){
		return new ProcedureScanner(tm, tp);
	}
	public ArgumentScanner getArgumentScanner() {
		return new ArgumentScanner(tm, tp);
	}

	public <T> RowScanner<T> getRowScanner(Mapper<T> mapper){
		return new RowScanner<T>(tm, tp, mapper);
	}
	public HeaderScanner getHeaderScanner(){
		return new HeaderScanner(tm, tp);
	}

	public UpdateExecutor getUpdateExecutor() {
		return new UpdateExecutor(tm, tp);
	}
	
	public BatchExecutor getBatchExecutor() {
		return new BatchExecutor(tm, tp);
	}
	
	public TransactionManager getTransactionManager() {
		return tm;
	}

}
