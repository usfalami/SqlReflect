package usf.java.sqlreflect.sql.type;

public enum ServerConstants {
	
	CATALOG("CAT"), SCHEMA("SCHEM");

	private ServerConstants(String suffix) {
		this.TABLE_DATABASE += suffix;
		this.PROCEDURE_DATABASE += suffix;
		this.FUNCTION_DATABASE += suffix;
	}
	
	public String 
		TABLE_DATABASE = "TABLE_", 
		PROCEDURE_DATABASE = "PROCEDURE_",
		FUNCTION_DATABASE = "FUNCTION_"
	;
}
