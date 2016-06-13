package fr.stm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;

import junit.framework.TestCase;
import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.connection.SimpleConnectionManager;
import usf.java.sql.db.Env;
import usf.java.sql.db.Server;
import usf.java.sql.db.User;
import usf.java.sql.db.server.TeradataServer;
import usf.java.sql.formatter.AsciiFormatter;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.scanner.ProcedureValidator;
import usf.java.sql.reflect.adapter.scanner.AbstractScannerAdapter.Validator;

public class StmFullScan extends TestCase {

private static Server db = new TeradataServer();
	
	private static Env env_ra4 = new Env("BDD_STM_DEV", "STM_IHM_RA4", 1025, "tmode=tera,charset=utf8");
	private static User user_ra4 = new User("stm_dba_ra4", "stm_dba_ra4");
	private static ConnectionManager cm = new SimpleConnectionManager(db, env_ra4, user_ra4);
	
	public static Formatter format = new AsciiFormatter(System.out);

	public void testEx1(){
		
		Properties p = new Properties();
		
		try {
			p.loadFromXML(new FileInputStream("C:/dev/workspace/STM/webapp/ihm/src/main/resources/pom_sql_queries.xml"));
			Enumeration<String> it = (Enumeration<String>) p.propertyNames();
			
			format =  new AsciiFormatter(new FileOutputStream("target/pom_sql_queries.txt")); 
			Validator a = new ProcedureValidator(cm, format);
			
			while(it.hasMoreElements()){
				String value = p.getProperty(it.nextElement()).trim().replace("<schemaSuffix>", "_RA4");
				if(db.parseFunction(value) != null){
					a.validate(value);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
