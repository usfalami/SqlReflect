package usf.java.sql.core.reflect;

import usf.java.sql.core.connection.transaction.TransactionManager;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.executor.BatchExecutor;
import usf.java.sql.core.reflect.executor.UpdateExecutor;
import usf.java.sql.core.reflect.scanner.ColumnScanner;
import usf.java.sql.core.reflect.scanner.DatabaseScanner;
import usf.java.sql.core.reflect.scanner.HasColumn;
import usf.java.sql.core.reflect.scanner.HeaderScanner;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;
import usf.java.sql.core.reflect.scanner.RowScanner;
import usf.java.sql.core.reflect.scanner.TableScanner;

public class ReflectorFactory {
	
	private TransactionManager tm;
	
	public ReflectorFactory(TransactionManager tm) {
		this.tm = tm;
	}
	
	public ColumnScanner getColumnScanner(HasColumn field) {
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
		return new UpdateExecutor(tm, true);
	}
	
	public BatchExecutor getBatchExecutor() {
		return new BatchExecutor(tm, true);
	}
	
	public TransactionManager getTransactionManager() {
		return tm;
	}

}
