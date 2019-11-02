package com.rabi.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class App {

	private static final String SINGLE_RABI_SER = "singleRabi.ser";

	public static void main(String[] args) 
				throws Exception {
		
		LazyRabiSingleton lz1=LazyRabiSingleton.getInstance();
		LazyRabiSingleton lz2=LazyRabiSingleton.getInstance();
		System.out.println(lz1.hashCode());
		System.out.println(lz2.hashCode());//same value as lz1
		
		//Prevent Object creation by clone()
		/*LazyRabiSingleton lz3Clone=(LazyRabiSingleton)lz2.clone();
		System.out.println(lz3Clone.hashCode());*/
		
		//Prevent Object Creation by Reflection
		/*LazyRabiSingleton lz3Reflec = populateObjectByReflection();
		System.out.println(lz3Reflec.hashCode());*/
		
		//Fixing Singleton break by Serialization/de-serialization
		LazyRabiSingleton lz3Serialize = populateObjectByDeserialization(lz1);
		System.out.println(lz3Serialize.hashCode());//same value as lz1
		
		
	}

	private static LazyRabiSingleton 
			populateObjectByDeserialization(LazyRabiSingleton lz1)
			throws IOException, FileNotFoundException, ClassNotFoundException {
		//Serialize  Singleton Object-->FILE
		ObjectOutput out= new ObjectOutputStream(
				new FileOutputStream(SINGLE_RABI_SER));
		out.writeObject(lz1);
		out.close();
		
		//De-Serialize  FILE-->Singleton Object
		ObjectInput in= new ObjectInputStream(
				new FileInputStream(SINGLE_RABI_SER));
		LazyRabiSingleton lz3Serialize
				= (LazyRabiSingleton)in.readObject();
		in.close();
		return lz3Serialize;
	}

	private static LazyRabiSingleton populateObjectByReflection()
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		LazyRabiSingleton lz3Reflec=null;
		Constructor[] constArrays
		      =LazyRabiSingleton.class.getDeclaredConstructors();
		for(Constructor constr:constArrays) {
			constr.setAccessible(true);
			lz3Reflec= (LazyRabiSingleton)constr.newInstance();
		}
		return lz3Reflec;
	}

}
