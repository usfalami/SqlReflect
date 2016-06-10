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
import usf.java.sql.reflect.adapter.scanner.AbstractScannerAdapter.Validator;
import usf.java.sql.reflect.adapter.scanner.ProcedureValidator;

public class StmQueriesTest extends TestCase {

	
	private static Server db = new TeradataServer();
	private static Env env = new Env("BDD_STM_PRA", "STM_IHM_PF1", 1025, "tmode=tera,charset=utf8");
	private static User user = new User("STM_DBA_PF1", "BY9HLCYB");
	
	private static ConnectionManager cm = new SimpleConnectionManager(db, env, user);
	
	public static Formatter format = new AsciiFormatter(System.out);
	
	public void testEx1(){
		
		Properties p = new Properties();
		
		try {
			p.loadFromXML(new FileInputStream("C:/dev/workspace/STM/webapp/ihm/src/main/resources/pom_sql_queries.xml"));
			Enumeration<String> it = (Enumeration<String>) p.propertyNames();
			
			format =  new AsciiFormatter(new FileOutputStream("target/pom_sql_queries.txt")); 
			Validator a = new ProcedureValidator(cm, format);
			
			while(it.hasMoreElements()){
				String value = p.getProperty(it.nextElement()).trim().replace("<schemaSuffix>", "_PF1");
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
