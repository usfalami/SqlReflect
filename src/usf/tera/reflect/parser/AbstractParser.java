package usf.tera.reflect.parser;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import usf.tera.reflect.AbstractReflect;
import usf.tera.reflect.adpter.ParserAdapter;

public abstract class AbstractParser<T extends ParserAdapter> extends AbstractReflect<T> {
	
	
	public final void lookup(String name) throws SQLException {
		if(adapter == null) return; //throw exception
		Connection cnx = null;
		try {
			cnx = rf.newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			lookup(dm, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(cnx!=null) cnx.close();
			//log("Connection closed");
		}
	}
	
	protected abstract void lookup(DatabaseMetaData dm, String name) throws SQLException;
	
	public void lookup() throws SQLException{
		lookup(null);
	}

}
