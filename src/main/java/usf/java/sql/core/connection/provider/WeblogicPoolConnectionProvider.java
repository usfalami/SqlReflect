package usf.java.sql.core.connection.provider;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class WeblogicPoolConnectionProvider extends PoolConnectionProvider {

	public WeblogicPoolConnectionProvider(Hashtable<String,String> env, String jndi) throws NamingException {
		configure(env, jndi);
	}
	
	public WeblogicPoolConnectionProvider(String url, String jndi) throws NamingException {
		Hashtable<String,String> env = new Hashtable<String,String>();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
	    env.put(Context.PROVIDER_URL, url);
	    configure(env, jndi);
	}
	
	protected void configure(Hashtable<String,String> env, String jndi) throws NamingException{
	    InitialContext ctx = new InitialContext(env);
	    ds = (DataSource) ctx.lookup(jndi);
	}
}
