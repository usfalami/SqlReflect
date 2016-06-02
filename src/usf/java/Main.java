package usf.java;

import java.sql.SQLException;
import java.text.ParseException;

import usf.java.adapter.Adapter;
import usf.java.adapter.executor.ExecutorColumnAdapter;
import usf.java.adapter.executor.ExecutorPerformAdapter;
import usf.java.adapter.executor.ExecutorResultAdapter;
import usf.java.adapter.parser.ParserCheckAdapter;
import usf.java.adapter.parser.ParserPrintAdapter;
import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.db.User;
import usf.java.db.type.Teradata;
import usf.java.field.SQL;
import usf.java.formatter.AsciiFormatter;
import usf.java.formatter.Formatter;
import usf.java.reflect.ReflectFactory;
import usf.java.reflect.executor.Executor;
import usf.java.reflect.parser.ProcedureParser;
import usf.java.reflect.parser.SchemaParser;

public class Main {

	private static Database db = new Teradata();
	private static Env env = new Env("BDD_STM_PRA", "STM_IHM_PF1", 1025);
	private static User user = new User("STM_DBA_PF1", "BY9HLCYB");
	
	private static ReflectFactory factory = ReflectFactory.get(db, env, user);
	
	public static final Formatter format = new AsciiFormatter(System.out);
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ParseException {
//		ex1();
//		ex2();
//		ex3();
		//test1();
			test2(); 
		//test3(); 
	}
	
	//Parsers & Adapters
	public static void ex1() throws InstantiationException, IllegalAccessException, SQLException{
		Adapter a = new ParserPrintAdapter(format);
		factory.get(ProcedureParser.class, a).lookup();
	}
	public static void ex2() throws InstantiationException, IllegalAccessException, SQLException{
		Adapter a = new ParserPrintAdapter(format);
		factory.get(SchemaParser.class, a).lookup();
	}
	public static void ex3() throws InstantiationException, IllegalAccessException, SQLException{
		SQL sql = factory.parseSQL(Queries.query2);
		Adapter a = new ParserCheckAdapter(format, sql);
		factory.get(ProcedureParser.class, a).lookup(sql.getName());
	}
	//Excecutors & Adapters
	public static void test1() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		Adapter a = new ExecutorColumnAdapter(format);
		SQL sql = factory.parseSQL(Queries.query2);
		factory.get(Executor.class, a).exec(sql);
	}
	public static void test2() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		Adapter a = new ExecutorPerformAdapter(format);
		SQL sql = factory.parseSQL(Queries.query2);
		for(int i=0; i<5; i++)
			factory.get(Executor.class, a).exec(sql);
	}
	public static void test3() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		Adapter a = new ExecutorResultAdapter(format);
		SQL sql = factory.parseSQL(Queries.query2);
		factory.get(Executor.class, a).exec(sql);
	}
}