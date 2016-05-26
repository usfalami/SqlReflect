package usf.tera.reflect;

import java.sql.Connection;

import usf.tera.reflect.ReflectFactory.Env;
import usf.tera.reflect.adpter.Adapter;

public abstract class AbstractReflect<T extends Adapter> {

	protected Connection con;
	protected Env env;
	protected T adapter;

	protected AbstractReflect(){
		
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	public void setEnvoronnement(Env env) {
		this.env = env;
	}
	public void setAdapter(T adapter) {
		this.adapter = adapter;
	}
	
}
