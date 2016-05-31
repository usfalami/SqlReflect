package usf.java.reflect;

import usf.java.adapter.Adapter;

public abstract class AbstractReflect<T extends Adapter> {

	protected ReflectFactory rf;
	protected T adapter;

	protected AbstractReflect(){
		
	}
	protected void setReflectFactory(ReflectFactory rf) {
		this.rf = rf;
	}
	protected void setAdapter(T adapter) {
		this.adapter = adapter;
	}
	
}