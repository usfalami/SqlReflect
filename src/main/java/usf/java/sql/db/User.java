package usf.java.sql.db;

public class User {
	
	private String user, pass;

	public User(String login, String pass) {
		this.user = login;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}
}
