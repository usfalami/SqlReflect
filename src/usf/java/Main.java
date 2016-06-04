package usf.java;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import usf.java.adapter.executor.ExecutorColumnAdapter;
import usf.java.adapter.executor.ExecutorPerformAdapter;
import usf.java.adapter.executor.ExecutorResultAdapter;
import usf.java.adapter.parser.ParserCheckAdapter;
import usf.java.adapter.parser.ParserPrintAdapter;
import usf.java.connection.ConnectionManager;
import usf.java.connection.SingleConnectionManager;
import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.db.User;
import usf.java.db.type.Teradata;
import usf.java.field.SQL;
import usf.java.formatter.AsciiFormatter;
import usf.java.formatter.Formatter;
import usf.java.reflect.executor.ExecutorAdapter;
import usf.java.reflect.parser.ParserAdapter;

public class Main {

	private static Database db = new Teradata();
	private static Env env = new Env("BDD_STM_PRA", "STM_IHM_PF1", 1025);
	private static User user = new User("STM_DBA_PF1", "BY9HLCYB");
	
	private static ConnectionManager factory = new SingleConnectionManager(db, env, user);
	
	public static Formatter format = new AsciiFormatter(System.out);

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	static String query = Queries.cap1_Bind;
	
	static Serializable[] param=null;
	
	
	static Serializable[][] par= null;
	
	static String[] it = {
		"exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-01-01' , '2015-01-01' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-01-01' , '2015-01-01' )",
		"exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-02-02' , '2015-02-02' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-02-02' , '2015-02-02' )",
		"exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-03-03' , '2015-03-03' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-03-03' , '2015-03-03' )",
		"exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-04-04' , '2015-04-04' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-04-04' , '2015-04-04' )",
		"exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-05-05' , '2015-05-05' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-05-05' , '2015-05-05' )",
		"exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-06-06' , '2015-06-06' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-06-06' , '2015-06-06' )",
		"exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-07-07' , '2015-07-07' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-07-07' , '2015-07-07' )"
	};
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ParseException, FileNotFoundException {
		
//			query = Queries.cap1_Bind;
//		param = new Serializable[]{
//				 new Date(sdf.parse("2016-05-27").getTime()),
//				 new Date(sdf.parse("2016-05-29").getTime()),
//				 "09781620756483"};
		
		par    = new Serializable[6][];
		par[0] = new Serializable[]{new Date(sdf.parse("2016-05-27").getTime()),new Date(sdf.parse("2016-05-29").getTime()), "09781620756483"};
		par[1] = new Serializable[]{new Date(sdf.parse("2013-02-15").getTime()),new Date(sdf.parse("2014-01-23").getTime()), "25482778573169"};
		par[2] = new Serializable[]{new Date(sdf.parse("2015-03-06").getTime()),new Date(sdf.parse("2016-05-29").getTime()), "25153256114306"};
		par[3] = new Serializable[]{new Date(sdf.parse("2014-05-11").getTime()),new Date(sdf.parse("2015-03-06").getTime()), "16185817549601"};
		par[4] = new Serializable[]{new Date(sdf.parse("2015-04-26").getTime()),new Date(sdf.parse("2016-05-29").getTime()), "5459768446848"};
		par[5] = new Serializable[]{new Date(sdf.parse("2014-01-23").getTime()),new Date(sdf.parse("2015-02-15").getTime()), "23102460063230"};

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
		
		test1();
//		test2(); 
//		test3();
//		ex3();
//		ex1();
//		ex2();
	}

	//Excecutors & Adapters
	public static void test1() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorPerformAdapter(factory, format);
		a.execute(query);
	}
	public static void test2() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorColumnAdapter(factory, format);
		a.execute(query, param);
	}
	public static void test3() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ExecutorAdapter a = new ExecutorResultAdapter(factory, format);
		a.execute(query, param);
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