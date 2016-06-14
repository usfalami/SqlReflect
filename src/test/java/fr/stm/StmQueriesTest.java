package fr.stm;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;

import junit.framework.TestCase;
import usf.java.sql.adapter.formatter.AsciiFormatter;
import usf.java.sql.adapter.formatter.CsvFormatter;
import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.formatter.HtmlFormatter;
import usf.java.sql.adapter.reflect.executor.AbstractExecutorAdapter;
import usf.java.sql.adapter.reflect.executor.ExecutorMultiAdapter;
import usf.java.sql.adapter.reflect.executor.ExecutorPerformAdapter;
import usf.java.sql.adapter.reflect.executor.ExecutorResultColumnAdapter;
import usf.java.sql.adapter.reflect.executor.ExecutorResultSetAdapter;
import usf.java.sql.adapter.reflect.scanner.DatabaseScannerPrinter;
import usf.java.sql.adapter.reflect.scanner.ProcedureColumnsComparator;
import usf.java.sql.adapter.reflect.scanner.ProcedureScannerPrinter;
import usf.java.sql.adapter.reflect.scanner.ProcedureValidator;
import usf.java.sql.adapter.reflect.scanner.AbstractScannerAdapter.Comparator;
import usf.java.sql.adapter.reflect.scanner.AbstractScannerAdapter.DatabasePrinter;
import usf.java.sql.adapter.reflect.scanner.AbstractScannerAdapter.Printer;
import usf.java.sql.adapter.reflect.scanner.AbstractScannerAdapter.Validator;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.connection.SimpleConnectionManager;
import usf.java.sql.core.db.Env;
import usf.java.sql.core.db.Server;
import usf.java.sql.core.db.User;
import usf.java.sql.core.db.server.TeradataServer;

public class StmQueriesTest extends TestCase {

	private static Server db = new TeradataServer();

	private static Env env_pf1 = new Env("BDD_STM_PRA", "STM_IHM_PF1", 1025, "tmode=tera,charset=utf8");
	private static User user_pf1 = new User("STM_DBA_PF1", "BY9HLCYB");
	
	private static ConnectionManager cm = new SimpleConnectionManager(db, env_pf1, user_pf1);
	
	public static Formatter format = new AsciiFormatter(System.out);
	
	static String query = Queries.cap1;
	static Serializable[] param=null;
	
	
	public void test() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		AbstractExecutorAdapter a = new ExecutorResultSetAdapter(cm, format);
		a.execute("SELECT 1", "SELECT database");
	}
	
	public void testExecutor1() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		AbstractExecutorAdapter a = new ExecutorPerformAdapter(cm, format);
		a.execute(query, param);
	}
	public void testExecutor2() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		AbstractExecutorAdapter a = new ExecutorResultColumnAdapter(cm, format);
		a.execute(query, param);
	}
	public void testExecutor3() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		AbstractExecutorAdapter a = new ExecutorResultSetAdapter(cm, format);
		a.execute(query, param);
	}
	
	public void testExecutor4() throws InstantiationException, IllegalAccessException, SQLException, ParseException, FileNotFoundException{
		ExecutorMultiAdapter a = new ExecutorMultiAdapter(cm, format);
		a.setAdapters( 
			//new ExecutorPerformAdapter(cm, new AsciiFormatter(System.out)),
			new ExecutorPerformAdapter(cm, new AsciiFormatter(new FileOutputStream("target/usf.txt"))),
			new ExecutorPerformAdapter(cm, new CsvFormatter(new FileOutputStream("target/usf.csv"))),
			new ExecutorPerformAdapter(cm, new HtmlFormatter(new FileOutputStream("target/usf.html")))
		);
		a.execute(query);
	}
	
	//Parsers & Adapters
	
	//list all server databases  
	public void testScanner1() throws InstantiationException, IllegalAccessException, SQLException{
		DatabasePrinter a = new DatabaseScannerPrinter(cm, format);
		a.list();
	}
	//Search PRCD_RECH_POM_ID_HAB_020 procedure in all Databases 
	public void testScanner2() throws InstantiationException, IllegalAccessException, SQLException{
		Printer a = new ProcedureScannerPrinter(cm, format);
		a.list(null, "PRCD_RECH_POM_ID_HAB_020");
	}
	//Search any procdure that contains 'RECH' & 'POM' in current database
	public void testScanner3() throws InstantiationException, IllegalAccessException, SQLException{
		Printer a = new ProcedureScannerPrinter(cm, format);
		a.list(env_pf1.getDatabase(), "%RECH%POM%ID%");
	}
	//Search and compare PRCD_RECH_POM_ID_HAB_020 procedure environnement
	public void testScanner4() throws InstantiationException, IllegalAccessException, SQLException{
		Comparator a = new ProcedureColumnsComparator(cm, format);
		a.compare("PRCD_RECH_POM_ID_HAB_020");
	}
	//Check parameters call
	public void testScanner5() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		Validator a = new ProcedureValidator(cm, format);
		a.validate(Queries.pom_search_bind);
	}
	
}
