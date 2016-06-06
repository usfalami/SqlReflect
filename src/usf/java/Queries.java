package usf.java;


public class Queries {
	
	static String pom_search = "CALL STM_IHM_PF1.PRCD_RECH_POM_ID_HAB_020('PRM', '30000120139847', '0', CAST ('2016/02/15 23:59:59' AS TIMESTAMP(0) WITH TIME ZONE), 'OE15540N', 'POM_SEARCH_UI', 'SDT_IHM-OI-SU', 0, NULL, P_DEBUG_QRY,'N')";

	static String cap1 		= "exec STM_IHM_pf1.MACR_RECH_SEGM_PRM_C1C5_SES ( '2016-05-27' , '2016-05-29' , '09781620756483' )";
	static String cap1_Bind = "exec STM_IHM_PF1.MACR_RECH_SEGM_PRM_C1C5_SES(?, ?, ?)";

	static String cap2		= "exec STM_IHM_pf1.MACR_CONS_MESR_C5EM_PDC_PARL_SES ( '2013-07-01' , '2015-07-01' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-07-01' , '2015-01-01' )";
	static String cap2_Bind	= "exec STM_IHM_pf1.MACR_CONS_MESR_C5EM_PDC_PARL_SES (?,?,?,?,?,?,?,?,?)";
	
	static String cap3 		= "exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES ( '2013-07-01' , '2015-07-01' , '19090984' , 30 , 'CONS' , 'EA' , 'COMPB' , '2014-07-01' , '2015-01-01' )";
	static String cap3_Bind	= "exec STM_IHM_pf1.MACR_CONS_MESR_C1C4_PDC_PARL_SES (?,?,?,?,?,?,?,?,?)";
	
	
	static String[] cap = {cap1, cap2, cap3};
	
}
