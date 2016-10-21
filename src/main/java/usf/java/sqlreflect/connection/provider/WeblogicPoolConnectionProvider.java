package usf.java.sqlreflect.connection.provider;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import usf.java.sqlreflect.server.User;

public class WeblogicPoolConnectionProvider extends PoolConnectionProvider {

	public WeblogicPoolConnectionProvider(Hashtable<String,String> env, String jndi) throws NamingException {
		configure(env, jndi);
	}
	
	public WeblogicPoolConnectionProvider(String url, User user, String jndi) throws NamingException {
		Hashtable<String,String> env = new Hashtable<String,String>();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
	    env.put(Context.PROVIDER_URL, url);
	    if(user != null){
	    	env.put(Context.SECURITY_PRINCIPAL, user.getLogin());
	    	env.put(Context.SECURITY_CREDENTIALS, user.getPass());
	    }
	    configure(env, jndi);
	}
	
	protected void configure(Hashtable<String,String> env, String jndi) throws NamingException{
	    InitialContext ctx = new InitialContext(env);
	    ds = (DataSource) ctx.lookup(jndi);
	}
	
}
