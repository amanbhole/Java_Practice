package com.demo.collection.array;

public class FindSecondHighest {

	public static void main(String[] args) {
		int[] array1 = {21,3,97,5,56,78,17,90};
		int[] array2 = {3,21,5,56,78,90,17};
		
		System.out.println("findSecondHighest : "+findSecondHighest(array1));
		System.out.println("findMissingElement : "+findMissingElement(array1, array2));
	}
	
	private static int findSecondHighest(int[] input) {
		int largest = 0;
		int secondLargest = 0;
		
		for (int i=0; i<input.length; i++) {
			if(input[i] > largest) {
				secondLargest = largest;
				largest = input[i];
			} else if (input[i] > secondLargest) {
				secondLargest = input[i];
			}
		}
		
		return secondLargest;
	}
	
	private static int findMissingElement(int[] input1, int[] input2) {
		
		int difference = Math.abs(input1.length - input2.length);
		if(difference!=1) {
			return -1;
		}
		int res = 0;
		for(int i=0; i<input1.length; i++) {
			res = res^input1[i];
		}
		for(int i=0; i<input2.length; i++) {
			res = res^input2[i];
		}
		return res;
	}
}
