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

#Database list

	List<Database> list = new DatabaseScanner(cm).run();

Schema | Database
-------|---------
SCH_1 | DATABASE_DEV1
SCH_1 | DATABASE_DEV2
SCH_1 | DATABASE_TEST
SCH_A | DATABASE_ISO
SCH_A | DATABASE_PROD

#Table list

	List<Table> list = new TableScanner(cm).run();

Schema | Table
-------|---------
SCH_1 | TABLE_CLIENT
SCH_1 | TABLE_PROVIDER
SCH_1 | TABLE_PRODUCT
SCH_1 | TABLE_ADRESS

#Query headers list

	List<Table> list = new HeaderScanner(cm).set("select * from DATABASE_DEV1.TABLE_PROVIDER").run();
	
HEADER_NAME |TYPE_NAME |LENGTH |CLASS |
COLUMN_1 |BIGINT     |20      |java.lang.Long     |
COLUMN_2 |CHAR       |10      |java.lang.String   |
COLUMN_3 |VARCHAR    |32      |java.lang.String   |
COLUMN_4 |VARCHAR    |256     |java.lang.String   |
COLUMN_5 |DATE       |10      |java.sql.Date      |
COLUMN_6 |DATE       |10      |java.sql.Date      |
COLUMN_7 |BYTEINT    |4       |java.lang.Integer  |
COLUMN_8 |INTEGER    |11      |java.lang.Integer  |

	
#Execution mesure time

	TimePerform perf = new ExecutorPerformer(cm).set("SELECT DATABASE").run();

Action | Start | End | Duration
-------|-------|-----|-----------------------------
Connection  |11:43:51.210  |11:43:54.797  | 3587 
Statment    |11:43:54.797  |11:43:54.797  |    0 
Execution   |11:43:54.797  |11:43:54.860  |   63 
Total       |11:43:51.210  |11:43:54.875  | 3665  


