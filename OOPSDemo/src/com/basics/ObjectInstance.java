package com.basics;

public class ObjectInstance implements Cloneable {

	@SuppressWarnings("unused")
	private void objByNewOperator() {
		ObjectInstance classObject = new ObjectInstance();
	}

	@SuppressWarnings("unused")
	private void objByNewIntanceMethod() {
		try {
			ObjectInstance.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void objByCloneMethod() {
		try {
			ObjectInstance classObject = new ObjectInstance();
			ObjectInstance classObject2 = (ObjectInstance) classObject.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void objAnonymous() {
		//This is anonymous object because this object is not being hold by any reference.
		new ObjectInstance();
	}
}
