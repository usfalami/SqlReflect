package usf.java;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import usf.java.adapter.Adapter;
import usf.java.adapter.executor.ExecutorColumnAdapter;
import usf.java.adapter.executor.ExecutorPerformAdapter;
import usf.java.adapter.executor.ExecutorResultAdapter;
import usf.java.adapter.parser.ParserCheckAdapter;
import usf.java.adapter.parser.ParserPrintAdapter;
import usf.java.db.Database;
import usf.java.db.Env;
import usf.java.db.Teradata;
import usf.java.db.User;
import usf.java.field.SQL;
import usf.java.reflect.ReflectFactory;
import usf.java.reflect.executor.Executor;
import usf.java.reflect.parser.ProcedureParser;
import usf.java.reflect.parser.SchemaParser;

public class Main {
	
	private static User user = new User("stm_dba_ra4", "stm_dba_ra4");
	private static Env env = new Env("ZED395A1", "STM_IHM_RA4", 1025);
	private static Database db = new Teradata();
	
//	private static ReflectFactory factory = ReflectFactory.get(db, env, user);
	private static ReflectFactory factory = ReflectFactory.get(db, new Env("BDD_STM_PRA", "STM_IHM_PF1", 1025), new User("STM_DBA_PF1", "BY9HLCYB"));
	
	private static String query = "call STM_IHM_RA4.PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020(CAST ('2015/12/15' AS DATE FORMAT 'YYYY/MM/DD'),CAST ('2016/02/15' AS DATE FORMAT 'YYYY/MM/DD'),NULL,NULL,NULL,NULL,'GASP',NULL,NULL,NULL,'OE15540N','SUPERVISION_COMPTAGE_INDUSTRIEL_UI','SDT_IHM-OI-SU',0,NULL,P_DEBUG_QRY,'I')";
	
	private static String queri = "call STM_IHM_RA4.PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020(CAST ('2015/12/15' AS DATE FORMAT 'YYYY/MM/DD'),CAST ('2016/02/15' AS DATE FORMAT 'YYYY/MM/DD'),NULL,NULL,NULL,NULL,'GASP',NULL,NULL,NULL,?,?,?,0,NULL,P_DEBUG_QRY,'I')";
	private static String query2 = "CALL STM_IHM_PF1.PRCD_RECH_POM_ID_HAB_020('PRM', '30000120139847', '0', CAST ('2016/02/15 23:59:59' AS TIMESTAMP(0) WITH TIME ZONE), 'OE15540N', 'POM_SEARCH_UI', 'SDT_IHM-OI-SU', 0, NULL, P_DEBUG_QRY,'N')";
	private static String query3 = "show procedure PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020";
	
	private static String queryCurve = "CALL STM_IHM_PF1.PRCD_E_COUR_090_04_COVA (197424622, CAST ('2015/11/01 00:00:00' AS TIMESTAMP(0) WITH TIME ZONE), CAST ('2015/11/30 00:00:00' AS TIMESTAMP(0) WITH TIME ZONE), 'COVA', NULL, 'PREFACT', NULL, 'EA', O','X, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST ('2015/12/01 01:42:11' AS TIMESTAMP(0) WITH TIME ZONE), 'E_SUP_321', NULL, P_DEBUG_QRY, 'I')";
	private static String queryCurvep = "CALL STM_IHM_RA4.PRCD_E_COUR_090_04_COVA (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, P_DEBUG_QRY, 'I')";
	
	private static String macroNobi = "exec STM_IHM_PF1.MACR_RECH_SEGM_PRM_C1C5('1999-01-01', '2015-12-31', '1007749')";
	private static String macroBind = "exec STM_IHM_PF1.MACR_RECH_SEGM_PRM_C1C5(?, ?, ?)";
	
	static String pom  = "CALL STM_IHM_PF1.PRCD_RECH_POM_ID_HAB_020('PRM', '3000%','0', CAST ('2010/04/07 23:59:59' AS TIMESTAMP(0) WITH TIME ZONE), 'B29945',   'POM_SEARCH_UI', 'SDT_IHM-ARD-CONSULT, SDT_IHM-ACM-CONSULT, SDT_IHM-ATD-GEST-DONNEES, CEC_ERDFUTI, ACZ_SIRANO-NATRFX, ACZ_SIRANO-NATGLOBAL, BAJ_CONSULTATION', 2, NULL, P_DEBUG_QRY,'N')";
	static String pom2 = "CALL STM_IHM_PF1.PRCD_RECH_POM_ID_HAB_020('PRM', '%0','0', CAST ('2016/05/27 23:59:59' AS TIMESTAMP(0) WITH TIME ZONE), 'NP168A5N', 'POM_SEARCH_UI', 'SDT_IHM-ARD-CONSULT, SDT_IHM-ACM-CONSULT, SDT_IHM-ATD-GEST-DONNEES, CEC_ERDFUTI, ACZ_SIRANO-NATRFX, ACZ_SIRANO-NATGLOBAL, BAJ_CONSULTATION', 0, NULL, P_DEBUG_QRY,'N') ";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ParseException {
//		new TeraConfig().configure();
//		
//		macro();
		
//		ex1();
		ex2();
		ex3();
		
		test1(); 
		test2(); 
		test3(); 
	}
	
	public static void macro() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ReflectFactory factory2 = ReflectFactory.get(db, new Env("BDD_STM_PRA", "STM_IHM_PF1", 1025), new User("STM_DBA_PF1", "BY9HLCYB"));
		Adapter a = new ExecutorPerformAdapter();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-mm-dd");
//		factory2.get(Executor.class, a).exec(macroNobi);
//		factory2.get(Executor.class, a).exec(macroBind, new Serializable[]{
//				new Date(df.parse("1999-01-01").getTime()), 
//				new Date(df.parse("2015-12-31").getTime()), 
//				"1007749"});
	}


	//Parsers & Adapters
	public static void ex1() throws InstantiationException, IllegalAccessException, SQLException{
		Adapter a = new ParserPrintAdapter();
		factory.get(ProcedureParser.class, a).lookup();
	}
	public static void ex2() throws InstantiationException, IllegalAccessException, SQLException{
		Adapter a = new ParserPrintAdapter();
		factory.get(SchemaParser.class, a).lookup();
	}
	public static void ex3() throws InstantiationException, IllegalAccessException, SQLException{
		SQL sql = factory.getDatabase().build(query2);
		Adapter a = new ParserCheckAdapter(sql);
		factory.get(ProcedureParser.class, a).lookup(sql.getName());
	}
	//Excecutors & Adapters
	public static void test1() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		Adapter a = new ExecutorColumnAdapter();
		SQL sql = factory.getDatabase().build(query2);
		factory.get(Executor.class, a).exec(sql);
	}
	public static void test2() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		Adapter a = new ExecutorPerformAdapter();
		SQL sql = factory.getDatabase().build(query2);
		factory.get(Executor.class, a).exec(sql);
	}
	public static void test3() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		Adapter a = new ExecutorResultAdapter();
		SQL sql = factory.getDatabase().build(query2);
		factory.get(Executor.class, a).exec(sql);
	}
//	public static void test4() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
//		Adapter a = new ExecutorPerformAdapter();
//		factory.get(MultiExecutor.class, a).exec(100, pom2);
//	}
//	public static void test5() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
//		Adapter a = new ExecutorPerformAdapter();
//		factory.get(MultiExecutor.class, a).exec(pom2, macroNobi, query2, queryCurve);
//	}
	
}