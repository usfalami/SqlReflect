package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.executor.BatchExecutor;
import usf.java.sqlreflect.reflect.executor.MultiQueryExecutor;
import usf.java.sqlreflect.reflect.executor.UpdateExecutor;
import usf.java.sqlreflect.reflect.scanner.data.HeaderScanner;
import usf.java.sqlreflect.reflect.scanner.data.RowScanner;
import usf.java.sqlreflect.reflect.scanner.field.ArgumentScanner;
import usf.java.sqlreflect.reflect.scanner.field.ColumnScanner;
import usf.java.sqlreflect.reflect.scanner.field.DatabaseScanner;
import usf.java.sqlreflect.reflect.scanner.field.ProcedureScanner;
import usf.java.sqlreflect.reflect.scanner.field.TableScanner;

public class ReflectorFactory {
	
	private TransactionManager tm;
	private ActionTimer at;
	
	public ReflectorFactory(TransactionManager tm, ActionTimer at) {
		this.tm = tm;
		this.at = at;
	}

	public DatabaseScanner getDatabaseScanner(){
		return new DatabaseScanner(tm, at.createAction());
	}
	
	public TableScanner getTableScanner(){
		return new TableScanner(tm, at.createAction());
	}
	public ColumnScanner getColumnScanner() {
		return new ColumnScanner(tm, at.createAction());
	}
	
	public ProcedureScanner geatrocedureScanner(){
		return new ProcedureScanner(tm, at.createAction());
	}
	public ArgumentScanner getArgumentScanner() {
		return new ArgumentScanner(tm, at.createAction());
	}

	public <A, R> RowScanner<A, R> getRowScanner(Mapper<R> mapper){
		return new RowScanner<A, R>(tm, at.createAction(), mapper);
	}
	public <A> HeaderScanner<A> getHeaderScanner(){
		return new HeaderScanner<A>(tm, at.createAction());
	}

	public <A> UpdateExecutor<A> getUpdateExecutor() {
		return new UpdateExecutor<A>(tm, at.createAction());
	}
	
	public <A> BatchExecutor<A> getBatchExecutor() {
		return new BatchExecutor<A>(tm, at.createAction());
	}
	
	public <A> MultiQueryExecutor<A> getMultiQueryExecutor() {
		return new MultiQueryExecutor<A>(tm, at.createAction());
	}
	
	
	public TransactionManager getTransactionManager() {
		return tm;
	}

}
