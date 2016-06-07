package usf.java.sql.db;

public class Env {

	protected String host;
	protected String schema;
	protected int port;
	protected String params;

	public Env(String host, String schema, int port) {
		this(host, schema, port, "");
	}

	public Env(String host, String schema, int port, String params) {
		this.host = host;
		this.schema = schema;
		this.params = params;
		this.port = port;
	}

	public String getHost() {
		return host;
	}
	public String getSchema() {
		return schema;
	}
	public int getPort() {
		return port;
	}
	public String getParams() {
		return params;
	}
}
