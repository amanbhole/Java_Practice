package com.demo.collections;

import java.util.HashSet;

class CollectionsDemo {

	public static void main(String[] arg) {
		CollectionsDemo ccd = new CollectionsDemo();
		
		ccd.setDemo();
		
	}

	private void setDemo() {
		HashSet<String> s1 = new HashSet<String>();

		s1.add("a1");
		s1.add("a1");
		s1.add("a4");
		s1.add("a1");
		s1.add("a3");

		System.out.println(s1.size());
		//TODO: Gives the result in sorted order. How?
		System.out.println(s1);
		System.out.println();
	}

}