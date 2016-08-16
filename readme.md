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

|Database
|---------
|DATABASE_DEV1
|DATABASE_DEV2
|DATABASE_TEST
|DATABASE_ISO
|DATABASE_PROD

#Table list

	List<Table> list = new TableScanner(cm).run();

Database | Table
---------|------
DATABASE_DEV1 | TABLE_CLIENT
DATABASE_DEV1 | TABLE_PROVIDER
DATABASE_DEV1 | TABLE_PRODUCT
DATABASE_PROD | TABLE_ADRESS


#Row list

	String query = "select * from DATABASE_DEV1.TABLE_PROVIDER";
	RowMapper mapper = new RowMapper();
	
	new RowScanner<Row>(cm, mapper).set(query).run();
	
COLUMN_1  |COLUMN_2 |COLUMN_3     |COLUMN_4 |COLUMN_5 |COLUMN_6
----------|---------|-------------|---------|---------|--------
1450741 |PRM    |30000120139847  |null |2004-05-01 |1
1450741 |IDC    |01201398        |null |2004-05-01 |1
1450741 |PADT-C |0000002377      |null |2004-05-01 |1

#Header description list

	String query = "select * from DATABASE_DEV1.TABLE_PROVIDER";
	
	List<Header> list = new HeaderScanner(cm).set(query).run();
	
HEADER_NAME |TYPE_NAME |LENGTH |CLASS
-------|-------|-----|---------------
COLUMN_1 |BIGINT  |20  |java.lang.Long   
COLUMN_2 |CHAR    |10  |java.lang.String 
COLUMN_3 |VARCHAR |32  |java.lang.String 
COLUMN_4 |VARCHAR |256 |java.lang.String 
COLUMN_5 |DATE    |10  |java.sql.Date    
COLUMN_6 |BYTEINT |4   |java.lang.Integer

	
#Execution mesure time

	TimePerform perf = new ExecutorPerformer(cm).set("SELECT DATABASE").run();

Action | Start | End | Duration
-------|-------|-----|-----------------------------
Connection  |11:43:51.210  |11:43:54.797  | 3587 
Statment    |11:43:54.797  |11:43:54.797  |    0 
Execution   |11:43:54.797  |11:43:54.860  |   63 
Total       |11:43:51.210  |11:43:54.875  | 3665  


