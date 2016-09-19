package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.executor.BatchExecutor;
import usf.java.sqlreflect.reflect.executor.UpdateExecutor;
import usf.java.sqlreflect.reflect.scanner.ColumnScanner;
import usf.java.sqlreflect.reflect.scanner.DatabaseScanner;
import usf.java.sqlreflect.reflect.scanner.SourceTypes;
import usf.java.sqlreflect.reflect.scanner.HeaderScanner;
import usf.java.sqlreflect.reflect.scanner.ProcedureScanner;
import usf.java.sqlreflect.reflect.scanner.RowScanner;
import usf.java.sqlreflect.reflect.scanner.TableScanner;

public class ReflectorFactory {
	
	private TransactionManager tm;
	
	public ReflectorFactory(TransactionManager tm) {
		this.tm = tm;
	}
	
	public ColumnScanner getColumnScanner(SourceTypes field) {
		return new ColumnScanner(tm, field);
	}

	public DatabaseScanner getDatabaseScanner(){
		return new DatabaseScanner(tm);
	}
	
	public HeaderScanner getHeaderScanner(){
		return new HeaderScanner(tm);
	}
	
	public ProcedureScanner getProcedureScanner(){
		return new ProcedureScanner(tm);
	}

	public <T> RowScanner<T> getRowScanner(Mapper<T> mapper){
		return new RowScanner<T>(tm, mapper);
	}
	
	public TableScanner getTableScanner(){
		return new TableScanner(tm);
	}
	
	public UpdateExecutor getUpdateExecutor() {
		return new UpdateExecutor(tm);
	}
	
	public BatchExecutor getBatchExecutor() {
		return new BatchExecutor(tm);
	}
	
	public TransactionManager getTransactionManager() {
		return tm;
	}

}
