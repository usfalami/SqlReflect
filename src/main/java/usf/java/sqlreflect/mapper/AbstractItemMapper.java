package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.sql.type.DatabaseType;

public abstract class AbstractItemMapper<T> implements Mapper<T> {
	
	private DatabaseType sc;
	
	@Override
	public void prepare(ResultSet rs) {}
	
	public DatabaseType getServerConstants() {
		return sc;
	}
	
	public void setDatabaseType(DatabaseType sc) {
		this.sc = sc;
	}

}
