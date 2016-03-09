package usf.tera.reflect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import usf.tera.ReflectFactory.Env;
import usf.tera.adpter.Adapter;

public abstract class Reflect {

	protected Connection con;
	protected Env env;
	protected Adapter adapter;

	protected Reflect(){
		
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	public void setEnvoronnement(Env env) {
		this.env = env;
	}
	public void setAdapter(Adapter adapter) {
		this.adapter = adapter;
	}
	
	public final void find(String name) throws SQLException {
		if(adapter == null || con == null || env==null) return;
		DatabaseMetaData dm = con.getMetaData();
		try {
			find(dm, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(con!=null) con.close();
			//log("Connection closed");
		}
	}
	
	protected abstract void find(DatabaseMetaData dm, String name) throws SQLException;
	
	public void findAll() throws SQLException{
		find(null);
	}
	
}
