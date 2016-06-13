package fr.stm;


public class Queries {
	
	public static String pom_search 		= "CALL STM_IHM_PF1.PRCD_RECH_POM_ID_HAB_020('PRM', '30000120139847', '0', CAST ('2016/02/15 23:59:59' AS TIMESTAMP(0) WITH TIME ZONE), 'OE15540N', 'POM_SEARCH_UI', 'SDT_IHM-OI-SU', 0, NULL, P_DEBUG_QRY,'N')";
	public static String pom_search_bind 	= "CALL STM_IHM_PF1.PRCD_RECH_POM_ID_HAB_020(?, ?, '0', ?, 'OE15540N', 'POM_SEARCH_UI', 'SDT_IHM-OI-SU', 0, NULL, P_DEBUG_QRY,'N')";

	
	
	public static String cap1 		= "exec STM_IHM_pf1.MACR_RECH_SEGM_PRM_C1C5_SES ( '2016-05-27' , '2016-05-29' , '09781620756483' )";
	public static String cap1_Bind = "exec STM_IHM_PF1.MACR_RECH_SEGM_PRM_C1C5_SES(?, ?, ?)";

	public static String cap2		= "exec STM_IHM_pf1.MACR_CONS_MESR_C5EM_PDC_PARL_SES ( '2013-07-01' , '2015-07-01' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-07-01' , '2015-01-01' )";
	public static String cap2_Bind	= "exec STM_IHM_pf1.MACR_CONS_MESR_C5EM_PDC_PARL_SES (?,?,?,?,?,?,?,?,?)";
	
	public static String cap3 		= "exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-07-01' , '2015-07-01' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-07-01' , '2015-01-01' )";
	public static String cap3_Bind	= "exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES (?,?,?,?,?,?,?,?,?)";
	
	
	public static String[] cap = {cap1, cap2, cap3};
	
//	cm.configure();
	
//	query = Queries.cap1_Bind;
//	param = new Serializable[]{
//			 new Date(sdf.parse("2016-05-27").getTime()),
//			 new Date(sdf.parse("2016-05-29").getTime()),
//			 "09781620756483"};
	
//	query = Queries.cap2_Bind;
//	query = Queries.cap3_Bind;
//	param = new Serializable[]{
//			 new Date(sdf.parse("2013-07-01").getTime()),
//			 new Date(sdf.parse("2015-07-01").getTime()),
//			 "19090984",
//			 30,
//			 "CONS",
//			 "EA",
//			 "COMPB",
//			 new Date(sdf.parse("2014-07-01").getTime()) , 
//			 new Date(sdf.parse("2015-01-01").getTime())};
	
}