package com.oops.polymorphism;

public class VariableInheritenceBase {

	int a = 10;
	
	public static void main(String[] args) {
		
		//Option 1 - ClassCastException
		//VariableInheritenceBase base = new VariableInheritenceBase();
		//int childVariable = ((Child)base).a;
		
		//Option 2
		VariableInheritenceBase base = new Child();
		int childVariable = ((Child)base).a;
		
		//Option 3 - Child takes parent variable
		//VariableInheritenceBase base = new Child2();
		//int childVariable = ((Child2)base).a;
		
		int baseVariable = base.a;
		
		System.out.println("Base : "+baseVariable);
		System.out.println("Child : "+childVariable);
	}
}

class Child extends VariableInheritenceBase {
	int a = 100;
}

class Child2 extends VariableInheritenceBase {
	
}
