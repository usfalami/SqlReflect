package usf.java.sqlreflect.server;

import java.util.Properties;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.Utils;

public class Env {

	private String host, database, params;
	private int port;

	public Env(String host, String database, int port) {
		this(host, database, port, "");
	}

	public Env(String host, String database, int port, String params) {
		this.host = host;
		this.database = database;
		this.params = Utils.isNull(params) ? "" : params;
		this.port = port;
	}
	
	public Env(Properties properties) {
		this.host = properties.getProperty(Constants.ENV_HOST);
		this.database = properties.getProperty(Constants.ENV_DATABASE);
		this.port = Integer.parseInt(properties.getProperty(Constants.ENV_PORT));
		this.params = properties.getProperty(Constants.ENV_PARAMS, "");
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
