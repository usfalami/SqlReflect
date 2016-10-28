package usf.java.sqlreflect.sql.type;

public enum DatabaseType {
	
	CATALOG("CAT"), SCHEMA("SCHEM");

	private DatabaseType(String suffix) {
		this.TABLE_DATABASE += suffix;
		this.PROCEDURE_DATABASE += suffix;
		this.FUNCTION_DATABASE += suffix;
	}
	
	public String 
		TABLE_DATABASE = "TABLE_", 
		PROCEDURE_DATABASE = "PROCEDURE_",
		FUNCTION_DATABASE = "FUNCTION_";
}
