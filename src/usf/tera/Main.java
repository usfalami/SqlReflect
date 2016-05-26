package usf.tera;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import usf.tera.ReflectFactory.Env;
import usf.tera.ReflectFactory.User;
import usf.tera.field.Procedure;
import usf.tera.reflect.adpter.Adapter;
import usf.tera.reflect.adpter.ExecutorColumnAdapter;
import usf.tera.reflect.adpter.ExecutorPerformAdapter;
import usf.tera.reflect.adpter.ExecutorResultAdapter;
import usf.tera.reflect.adpter.ParserCheckAdapter;
import usf.tera.reflect.adpter.ParserPrintAdapter;
import usf.tera.reflect.executor.Executor;
import usf.tera.reflect.parser.ProcedureParser;

public class Main {
	
	private static User user = new User("stm_dba_ra4", "stm_dba_ra4");
	private static Env env = new Env("ZED395A1", "STM_IHM_RA4");
	
	private static String query = "call STM_IHM_RA4.PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020(CAST ('2015/12/15' AS DATE FORMAT 'YYYY/MM/DD'),CAST ('2016/02/15' AS DATE FORMAT 'YYYY/MM/DD'),NULL,NULL,NULL,NULL,'GASP',NULL,NULL,NULL,'OE15540N','SUPERVISION_COMPTAGE_INDUSTRIEL_UI','SDT_IHM-OI-SU',0,NULL,P_DEBUG_QRY,'I')";
	
	
	private static String queri = "call STM_IHM_RA4.PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020(CAST ('2015/12/15' AS DATE FORMAT 'YYYY/MM/DD'),CAST ('2016/02/15' AS DATE FORMAT 'YYYY/MM/DD'),NULL,NULL,NULL,NULL,'GASP',NULL,NULL,NULL,?,?,?,0,NULL,P_DEBUG_QRY,'I')";
	private static String query2 = "CALL STM_IHM_RA4.PRCD_RECH_POM_ID_HAB_020('PRM', '30000120139847', '0', CAST ('2016/02/15 23:59:59' AS TIMESTAMP(0) WITH TIME ZONE), 'OE15540N', 'POM_SEARCH_UI', 'SDT_IHM-OI-SU', 0, NULL, P_DEBUG_QRY,'N')";
	private static String query3 = "show procedure PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020";
	
	
	private static String queryCurve = "CALL STM_IHM_RA4.PRCD_E_COUR_090_04_COVA (197424622, CAST ('2015/11/01 00:00:00' AS TIMESTAMP(0) WITH TIME ZONE), CAST ('2015/11/30 00:00:00' AS TIMESTAMP(0) WITH TIME ZONE), 'COVA', NULL, 'PREFACT', NULL, 'EA', O','X, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST ('2015/12/01 01:42:11' AS TIMESTAMP(0) WITH TIME ZONE), 'E_SUP_321', NULL, P_DEBUG_QRY, 'I')";
	private static String queryCurvep = "CALL STM_IHM_RA4.PRCD_E_COUR_090_04_COVA (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, P_DEBUG_QRY, 'I')";
	
	private static String macroNobi = "exec STM_IHM_PF1.MACR_RECH_SEGM_PRM_C1C5('1999-01-01', '2015-12-31', '300')";
	private static String macroBind = "exec STM_IHM_PF1.MACR_RECH_SEGM_PRM_C1C5(?, ?, ?)";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ParseException {
//		new TeraConfig().configure();
//		
		Procedure p = Procedure.build(query);
		if(p==null) {
			System.out.println("Procedure non valid");
			return;
		}
		
		ex2(p);
	}
	
	
	public static void macro() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ReflectFactory factory = new ReflectFactory(new Env("BDD_STM_PRA", "STM_IHM_PF1"), new User("STM_DBA_PF1", "BY9HLCYB"));
		Adapter a = new ExecutorColumnAdapter();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-mm-dd");
		factory.get(Executor.class, a).exec(macroNobi);
		factory.get(Executor.class, a).exec(macroBind, new Serializable[]{
				new Date(df.parse("1999-01-01").getTime()), 
				new Date(df.parse("2015-12-31").getTime()), 
				"90216111111177"});
	}
	
	public static void test5() throws InstantiationException, IllegalAccessException, SQLException, ParseException{
		ReflectFactory factory = new ReflectFactory(env, user);
		Adapter a = new ExecutorPerformAdapter();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-mm-dd");
		factory.get(Executor.class, a).exec(query2);
	}
	
	
	public static void ex1(Procedure p) throws InstantiationException, IllegalAccessException, SQLException{
		ReflectFactory factory = new ReflectFactory(env, user);
		Adapter a = new ParserPrintAdapter(System.out);
		factory.get(ProcedureParser.class, a).findAll();
	}
	
	public static void ex2(Procedure p) throws InstantiationException, IllegalAccessException, SQLException{
		ReflectFactory factory = new ReflectFactory(env, user);
		Adapter a = new ParserCheckAdapter(System.out, p);
		factory.get(ProcedureParser.class, a).find(p.getName());
	}

}