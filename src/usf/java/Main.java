package usf.java;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import usf.java.connection.ConnectionManager;
import usf.java.connection.SingleConnectionManager;
import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.db.User;
import usf.java.db.type.Teradata;
import usf.java.field.SQL;
import usf.java.formatter.AsciiFormatter;
import usf.java.formatter.Formatter;
import usf.java.formatter.HtmlFormatter;
import usf.java.reflect.executor.adapter.ExecutorAdapter;
import usf.java.reflect.executor.adapter.ExecutorColumnAdapter;
import usf.java.reflect.executor.adapter.ExecutorPerformAdapter;
import usf.java.reflect.executor.adapter.ExecutorResultAdapter;
import usf.java.reflect.executor.adapter.MultiExecutorAdapter;
import usf.java.reflect.parser.adapter.ParserAdapter;
import usf.java.reflect.parser.adapter.ParserCheckAdapter;
import usf.java.reflect.parser.adapter.ParserPrintAdapter;

public class Main {

	private static Database db = new Teradata();
	private static Env env = new Env("BDD_STM_PRA", "STM_IHM_PF1", 1025);
	private static User user = new User("STM_DBA_PF1", "BY9HLCYB");
	
	private static ConnectionManager factory = new SingleConnectionManager(db, env, user);
	
	public static Formatter format = new AsciiFormatter(System.out);

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static String query = Queries.cap1;
	static Serializable[] param=null;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ParseException, FileNotFoundException {
		
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
		System.out.println(query);
		
//		test1();
//		test2(); 
//		test3();
		test4();
//		ex3();
//		ex1();
//		ex2();
	}

	//Excecutors & Adapters
	public static void test1() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorPerformAdapter(factory, format);
		a.execute(query, param);
	}
	public static void test2() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorColumnAdapter(factory, format);
		a.execute(query, param);
	}
	public static void test3() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorResultAdapter(factory, format);
		a.execute(query, param);
	}
	
	public static void test4() throws InstantiationException, IllegalAccessException, SQLException, ParseException, FileNotFoundException{
//		OutputStream out  = new FileOutputStream("output/usf.html");
		MultiExecutorAdapter a = new MultiExecutorAdapter(factory, format);
		a.setAdapters( 
			new ExecutorResultAdapter(factory, new AsciiFormatter(System.out)),
			new ExecutorPerformAdapter(factory, new AsciiFormatter(System.out)),
			new ExecutorColumnAdapter(factory, new AsciiFormatter(System.out))
		);
		a.execute(5, query, param);
	}
	
	//Parsers & Adapters
	public static void ex1() throws InstantiationException, IllegalAccessException, SQLException{
		ParserAdapter a = new ParserPrintAdapter(factory, format);
		a.listSchema(null);
	}
	public static void ex2() throws InstantiationException, IllegalAccessException, SQLException{
		ParserAdapter a = new ParserPrintAdapter(factory, format);
		a.listProcedure(null, null);
	}
	public static void ex3() throws InstantiationException, IllegalAccessException, SQLException{
		SQL sql = factory.parseSQL(query);
		ParserAdapter a = new ParserCheckAdapter(factory, format, sql);
		a.listProcedure(null, sql.getName());
	}
	
}