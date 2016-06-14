package com.demo.collection.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

// Eliminates duplicates
class SetDemo {

	public static void main(String[] arg) {
		SetDemo setDemo = new SetDemo();
		
		setDemo.startSetDemo();
		System.out.println("----------------------");
		setDemo.startSortedSetDemo();
		System.out.println("----------------------");
		setDemo.startLinkeHashSetDemo();
	}

	//does not maintain any order
	private void startSetDemo() {
		Set<String> s1 = new HashSet<String>();

		s1.add("first");
		s1.add("first");
		s1.add("first");
		s1.add("third");
		s1.add("second");

		System.out.println(String.format("Size of set is : %d", s1.size()));
		System.out.println(String.format("Set collection content : %s",s1));
		
		//Basic operations
		System.out.println(String.format("Is HashSet empty? %s",s1.isEmpty()));
        s1.remove("third");
        System.out.println(s1);
        System.out.println(String.format("Size of the HashSet: %s",s1.size()));
        System.out.println(String.format("Does HashSet contains first element? %s",s1.contains("first")));

	}
	
	//sorts the content
	private void startSortedSetDemo() {
		SortedSet<String> s1 = new TreeSet<String>();

		s1.add("first");
		s1.add("first");
		s1.add("first");
		s1.add("third");
		s1.add("second");

		System.out.println(String.format("Size of sorted set is : %d", s1.size()));
		System.out.println(String.format("Sorted Set collection content : %s",s1));
	}

	//maintains insertion order
	private void startLinkeHashSetDemo() {
		Set<String> s1 = new LinkedHashSet<String>();

		s1.add("first");
		s1.add("first");
		s1.add("first");
		s1.add("third");
		s1.add("second");

		System.out.println(String.format("Size of linked hash set is : %d", s1.size()));
		System.out.println(String.format("Linked Hash Set collection content : %s",s1));
	}
}