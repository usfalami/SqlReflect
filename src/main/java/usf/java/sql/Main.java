package usf.java.sql;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.connection.SimpleConnectionManager;
import usf.java.sql.db.Env;
import usf.java.sql.db.Server;
import usf.java.sql.db.User;
import usf.java.sql.db.server.TeradataServer;
import usf.java.sql.formatter.AsciiFormatter;
import usf.java.sql.formatter.CsvFormatter;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.formatter.HtmlFormatter;
import usf.java.sql.reflect.adapter.executor.ExecutorAdapter;
import usf.java.sql.reflect.adapter.executor.ExecutorColumnAdapter;
import usf.java.sql.reflect.adapter.executor.ExecutorPerformAdapter;
import usf.java.sql.reflect.adapter.executor.ExecutorResultAdapter;
import usf.java.sql.reflect.adapter.executor.MultiExecutorAdapter;
import usf.java.sql.reflect.adapter.parser.AbstractDatabaseScanner;
import usf.java.sql.reflect.adapter.parser.AbstractFunctionScnner;
import usf.java.sql.reflect.adapter.parser.DatabaseScannerPrinter;
import usf.java.sql.reflect.adapter.parser.FunctionColumnComparator;
import usf.java.sql.reflect.adapter.parser.FunctionScannerPrinter;

public class Main {

	private static Server db = new TeradataServer();
	private static Env env = new Env("BDD_STM_PRA", "STM_IHM_PF1", 1025, "tmode=tera,charset=utf8");
	private static User user = new User("STM_DBA_PF1", "BY9HLCYB");
	
	private static ConnectionManager cm = new SimpleConnectionManager(db, env, user);
	
	public static Formatter format = new AsciiFormatter(System.out);

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static String query = Queries.cap1;
	static Serializable[] param=null;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ParseException, FileNotFoundException {
		
		cm.configure();
		
//		query = Queries.cap1_Bind;
//		param = new Serializable[]{
//				 new Date(sdf.parse("2016-05-27").getTime()),
//				 new Date(sdf.parse("2016-05-29").getTime()),
//				 "09781620756483"};
		
//		query = Queries.cap2_Bind;
//		query = Queries.cap3_Bind;
//		param = new Serializable[]{
//				 new Date(sdf.parse("2013-07-01").getTime()),
//				 new Date(sdf.parse("2015-07-01").getTime()),
//				 "19090984",
//				 30,
//				 "CONS",
//				 "EA",
//				 "COMPB",
//				 new Date(sdf.parse("2014-07-01").getTime()) , 
//				 new Date(sdf.parse("2015-01-01").getTime())};
		
		//format = new AsciiFormatter(new FileOutputStream("output/usf.txt"));
//		
//		test();
//		test1();
//		test2(); 
//		test3();
//		test4();
		
		ex1();
		ex2();
		ex3();
		ex4();
	}

	//Excecutors & Adapters
	
	public static void test() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorResultAdapter(cm, format);
		a.execute("SELECT 1", "SELECT database");
	}
	
	public static void test1() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorPerformAdapter(cm, format);
		a.execute(query, param);
	}
	public static void test2() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorColumnAdapter(cm, format);
		a.execute(query, param);
	}
	public static void test3() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorResultAdapter(cm, format);
		a.execute(query, param);
	}
	
	public static void test4() throws InstantiationException, IllegalAccessException, SQLException, ParseException, FileNotFoundException{
		MultiExecutorAdapter a = new MultiExecutorAdapter(cm, format);
		a.setAdapters( 
			new ExecutorPerformAdapter(cm, new AsciiFormatter(System.out)),
			new ExecutorPerformAdapter(cm, new AsciiFormatter(new FileOutputStream("target/usf.txt"))),
			new ExecutorPerformAdapter(cm, new CsvFormatter(new FileOutputStream("target/usf.csv"))),
			new ExecutorPerformAdapter(cm, new HtmlFormatter(new FileOutputStream("target/usf.html")))
		);
		a.execute(Queries.cap);
	}
	
	//Parsers & Adapters
	
	//list all server databases  
	public static void ex1() throws InstantiationException, IllegalAccessException, SQLException{
		AbstractDatabaseScanner a = new DatabaseScannerPrinter(cm, format);
		a.list();
	}
	//Search PRCD_RECH_POM_ID_HAB_020 procedure in all Databases 
	public static void ex2() throws InstantiationException, IllegalAccessException, SQLException{
		AbstractFunctionScnner a = new FunctionScannerPrinter(cm, format);
		a.list(null, "PRCD_RECH_POM_ID_HAB_020");
	}
	//Search any procdure that contains 'RECH' & 'POM' in current database
	public static void ex3() throws InstantiationException, IllegalAccessException, SQLException{
		AbstractFunctionScnner a = new FunctionScannerPrinter(cm, format);
		a.list(env.getDatabase(), "%RECH%POM%");
	}
	//Search and compare PRCD_RECH_POM_ID_HAB_020 procedure environnement
	public static void ex4() throws InstantiationException, IllegalAccessException, SQLException{
		AbstractFunctionScnner a = new FunctionColumnComparator(cm, format);
		a.list(null, "PRCD_RECH_POM_ID_HAB_020");
	}
}