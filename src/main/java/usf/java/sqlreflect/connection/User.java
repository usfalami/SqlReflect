package usf.java.sqlreflect.connection;

import java.util.Properties;

import usf.java.sqlreflect.Constants;

public class User {
	
	private String login, pass;

	public User(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}
	
	public User(Properties properties) {
		this.login = properties.getProperty(Constants.USER_LOGIN);
		this.pass = properties.getProperty(Constants.USER_PASSWORD);
	}


	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}
}
