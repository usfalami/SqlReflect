package usf.java.sqlreflect.core.connection;

public class User {
	
	private String login, pass;

	public User(String login, String pass) {
		this.login = login;
		this.pass = pass;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}
}
