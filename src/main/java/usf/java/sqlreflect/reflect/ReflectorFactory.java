package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.connection.transaction.TransactionManager;
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
	
	public ReflectorFactory(TransactionManager tm) {
		this.tm = tm;
	}

	public DatabaseScanner getDatabaseScanner(){
		return new DatabaseScanner(tm);
	}
	
	public TableScanner getTableScanner(){
		return new TableScanner(tm);
	}
	public ColumnScanner getColumnScanner() {
		return new ColumnScanner(tm);
	}
	
	public ProcedureScanner getProcedureScanner(){
		return new ProcedureScanner(tm);
	}
	public ArgumentScanner getArgumentScanner() {
		return new ArgumentScanner(tm);
	}

	public <P, T> RowScanner<P, T> getRowScanner(Mapper<T> mapper){
		return new RowScanner<P, T>(tm, mapper);
	}
	public <P> HeaderScanner<P> getHeaderScanner(){
		return new HeaderScanner<P>(tm);
	}
	
	public TransactionManager getTransactionManager() {
		return tm;
	}
	public <P> UpdateExecutor<P> getUpdateExecutor() {
		return new UpdateExecutor<P>(tm);
	}
	
	public <P> BatchExecutor<P> getBatchExecutor() {
		return new BatchExecutor<P>(tm);
	}


}
