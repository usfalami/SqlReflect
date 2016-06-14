package usf.java.sql.core.field;

public class Env {

	protected String host;
	protected String database;
	protected int port;
	protected String params;

	public Env(String host, String database, int port) {
		this(host, database, port, "");
	}

	public Env(String host, String database, int port, String params) {
		this.host = host;
		this.database = database;
		this.params = params;
		this.port = port;
	}

	public String getHost() {
		return host;
	}
	public String getDatabase() {
		return database;
	}
	public int getPort() {
		return port;
	}
	public String getParams() {
		return params;
	}
}
