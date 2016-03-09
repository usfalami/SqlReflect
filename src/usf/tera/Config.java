package usf.tera;

public interface Config {
	
	void configure()  throws ClassNotFoundException ;
	
	public static class TeraConfig implements Config {
		
		public void configure() throws ClassNotFoundException {
			Class.forName("com.teradata.jdbc.TeraDriver");
		};
		
	}

}
