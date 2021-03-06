package usf.java.sqlreflect;

public interface Constants {

	String ENV_HOST 		= "env.host";
	String ENV_DATABASE 	= "env.database";
	String ENV_PORT 		= "env.port";
	String ENV_PARAMS 		= "env.params";
	String USER_LOGIN 		= "user.login";
	String USER_PASSWORD 	= "user.password";

	String ACTION_VALIDATION 	= "VALIDATION";
	String ACTION_CONNECTION 	= "CONNECTION";
	String ACTION_BINDING 		= "BINDING";
	String ACTION_EXECUTION 	= "EXECUTION";
	String ACTION_PREPARATION 	= "PREPARATION";
	String ACTION_PROCESSING 	= "PROCESSING";
	String ACTION_TRANSACTION 	= "TRANSACTION";
	String ACTION_ROLLBACK 		= "ROLLBACK";

	String TIMER_ACTION 	= "Action";
	String TIMER_START 		= "Start";
	String TIMER_END 		= "End";
	String TIMER_DURATION 	= "Duration";
	
	String PROPERTY_SETTER = "SETTER";
	String PROPERTY_GETTER = "GETTER";
	String OBJECT_WRITER 	= "WRITER";
	
}
