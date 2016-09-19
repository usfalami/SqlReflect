package usf.java.sqlreflect.item;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Procedure extends Callable {
	
	private String type;
	
	public Procedure() {
		super();
	}

	public Procedure(String database, String name) {
		super(database, name);
	}

	public Procedure(String sql) {
		super(sql);
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
