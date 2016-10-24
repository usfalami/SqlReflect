package usf.java.sqlreflect.mapper;

import usf.java.sqlreflect.sql.entry.item.Item;
import usf.java.sqlreflect.sql.type.ServerConstants;

public abstract class AbstractItemMapper<T extends Item> implements Mapper<T> {
	
	private ServerConstants sc;
	
	public AbstractItemMapper(ServerConstants sc) {
		this.sc = sc;
	}
	
	public ServerConstants getServerConstants() {
		return sc;
	}

}
