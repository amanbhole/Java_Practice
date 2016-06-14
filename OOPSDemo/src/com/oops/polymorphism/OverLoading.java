package com.oops.polymorphism;

public class OverLoading {

	int a = 10;
	
	public static void main(String[] args) {
		OverLoading loading = new B();
		System.out.println(loading.a);
		System.out.println(((B)loading).a);
	}
}

class B extends OverLoading {
	int a = 100;
}
