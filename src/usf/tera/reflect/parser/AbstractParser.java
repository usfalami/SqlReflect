package usf.tera.reflect.parser;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import usf.tera.reflect.AbstractReflect;
import usf.tera.reflect.adpter.ParserAdapter;

public abstract class AbstractParser<T extends ParserAdapter> extends AbstractReflect<T> {
	
	
	public final void lookup(String name) throws SQLException {
		if(adapter == null || con == null || env==null) return;
		DatabaseMetaData dm = con.getMetaData();
		try {
			lookup(dm, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) con.close();
			//log("Connection closed");
		}
	}
	
	protected abstract void lookup(DatabaseMetaData dm, String name) throws SQLException;
	
	public void lookup() throws SQLException{
		lookup(null);
	}

}
