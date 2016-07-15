package usf.java.sql.core.field;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Procedure extends Callable {
	
	protected Procedure(){
		super();
	}

	public Procedure(String database, String name) {
		super(database, name);
	}

	public Procedure(String sql) {
		super(sql);
	}

}
