JDBC tool


#Example

    Server server 	= new OracleServer(); //Oracle server for exemple, we can use other servers
	Env env 		= new Env("host_adress", "database_name", port, "parameters"); //define a environemment
	User user 		= new User("user_name", "user_password");	//define a user
	
	
	ConnectionProvider cp	= new SimpleConnectionProvider(server, env);		//define a connexion provider, we can use other providers like POOL
	ConnectionManager cm 	= new SimpleConnectionManager(cp, server, user);	//define a connexion manager
	
	
	
	
	