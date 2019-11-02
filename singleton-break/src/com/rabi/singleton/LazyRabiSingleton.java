package com.rabi.singleton;

import java.io.Serializable;

public class LazyRabiSingleton 
				extends MyClone implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static LazyRabiSingleton
	              instance= new LazyRabiSingleton() ;
	private LazyRabiSingleton() {
		if(instance!=null) {
		throw new IllegalStateException("Object creation no allowed");
		}
	}
	public static synchronized 
			LazyRabiSingleton getInstance() {
		return instance;
	}
	@Override
	protected Object clone() 
		throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	protected Object readResolve() {
		return instance;
	}

}
