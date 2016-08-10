#Environnement

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
	
#Execution mesure time

##Src
	
	TimePerform perf = new ExecutorPerformer(cm).set("SELECT DATABASE").run();
	
##Result	

Action | Start | End | Duration
-------|-------|-----|-----------------------------
Connection  |11:43:51.210  |11:43:54.797  | 3587 
Statment    |11:43:54.797  |11:43:54.797  |    0 
Execution   |11:43:54.797  |11:43:54.860  |   63 
Total       |11:43:51.210  |11:43:54.875  | 3665   


#Database list

##Src

	List<Database> list = new DatabaseScanner(cm).set(null).run();

##Result

Schema | Database
-------|---------
sche_1 | database_1
sche_1 | database_2
sche_1 | database_3
sche_A | database_A320
sche_A | database_A455

