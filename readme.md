JDBC tool


#Example

	//Oracle server for exemple, we can use other servers
    Server server 	= new OracleServer(); 
    
    //define a environemment
	Env env 		= new Env("host_adress", "database_name", port, "parameters"); 
	
	//define a user
	User user 		= new User("user_name", "user_password");
	
	//define a connexion provider, we can use other providers like POOL
	ConnectionProvider cp	= new SimpleConnectionProvider(server, env);
	
	//define a connexion manager
	ConnectionManager cm 	= new SimpleConnectionManager(cp, server, user);
	
	//
	TimePerformAdapter adapter = new TimePerformAdapter();
	new ExecutorPerformer(cm).set(Queries.pom_search).run(adapter);
	TimePerform perf = adapter.getTimePerform();

#Result	

+-----------------------------------------------------+
|Action      |Start         |End           |Duration  |
+-----------------------------------------------------+
|Connection  |11:08:22.901  |11:08:29.067  | 6166     |
|Statment    |11:08:29.067  |11:08:29.067  |    0     |
|Execution   |11:08:29.067  |11:08:45.148  |16081     |
|Total       |11:08:22.901  |11:08:45.164  |22263     |
+-----------------------------------------------------+