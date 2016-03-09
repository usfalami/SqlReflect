package usf.tera;

import java.sql.SQLException;
import java.text.ParseException;

import usf.tera.Config.TeraConfig;
import usf.tera.ReflectFactory.Env;
import usf.tera.ReflectFactory.User;
import usf.tera.adpter.Adapter;
import usf.tera.adpter.CheckAdapter;
import usf.tera.adpter.PrintAdapter;
import usf.tera.reflect.ProcedureReflect;
import usf.tera.reflect.field.Procedure;

public class Main {
	
	private static User user = new User("stm_dba_ra4", "stm_dba_ra4");
	private static Env env = new Env("ZED395A1", "STM_IHM_RA4");

	private static ReflectFactory factory = new ReflectFactory(env, user);
	
	
	private static String query = "call STM_IHM_RA4.PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020(CAST ('2015/12/15' AS DATE FORMAT 'YYYY/MM/DD'),CAST ('2016/02/15' AS DATE FORMAT 'YYYY/MM/DD'),NULL,NULL,NULL,NULL,'GASP',NULL,NULL,NULL,'OE15540N','SUPERVISION_COMPTAGE_INDUSTRIEL_UI','SDT_IHM-OI-SU',0,NULL,P_DEBUG_QRY,'I')";
	
	
	private static String queri = "call STM_IHM_RA4.PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020(CAST ('2015/12/15' AS DATE FORMAT 'YYYY/MM/DD'),CAST ('2016/02/15' AS DATE FORMAT 'YYYY/MM/DD'),NULL,NULL,NULL,NULL,'GASP',NULL,NULL,NULL,?,?,?,0,NULL,P_DEBUG_QRY,'I')";
	private static String query2 = "CALL STM_IHM_RA4.PRCD_RECH_POM_ID_HAB_020('PRM', '30000120139847', '0', CAST ('2016/02/15 23:59:59' AS TIMESTAMP(0) WITH TIME ZONE), 'OE15540N', 'POM_SEARCH_UI', 'SDT_IHM-OI-SU', 0, NULL, P_DEBUG_QRY,'N')";
	private static String query3 = "show procedure PRCD_E_SUP_240_HIST_PRCS_CMPT_HAB_020";
	
	
	private static String queryCurve = "CALL STM_IHM_RA4.PRCD_E_COUR_090_04_COVA (197424622, CAST ('2015/11/01 00:00:00' AS TIMESTAMP(0) WITH TIME ZONE), CAST ('2015/11/30 00:00:00' AS TIMESTAMP(0) WITH TIME ZONE), 'COVA', NULL, 'PREFACT', NULL, 'EA', O','X, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, CAST ('2015/12/01 01:42:11' AS TIMESTAMP(0) WITH TIME ZONE), 'E_SUP_321', NULL, P_DEBUG_QRY, 'I')";
	private static String queryCurvep = "CALL STM_IHM_RA4.PRCD_E_COUR_090_04_COVA (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, ?, ?, NULL, P_DEBUG_QRY, 'I')";
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, ParseException {
		new TeraConfig().configure();
		
		ex2();
	}
	
	
	public static void ex1() throws InstantiationException, IllegalAccessException, SQLException{
		Procedure p = Procedure.build(query);
		
		if(p==null) {
			System.out.println("Procedure non valid");
			return;
		}
		Adapter a = new PrintAdapter(System.out);
		factory.get(ProcedureReflect.class, a).findAll();
	}
	
	public static void ex2() throws InstantiationException, IllegalAccessException, SQLException{
		Procedure p = Procedure.build(query);
		
		if(p==null) {
			System.out.println("Procedure non valid");
			return;
		}
		Adapter a = new CheckAdapter(System.out, p);
		factory.get(ProcedureReflect.class, a).find(p.getName());
	}

}