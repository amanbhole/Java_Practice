package com.demo.collection.array;

//Find common elements from sorted arrays.
public class FindCommonElements {

	public static void main(String[] args) {
		int ar1[] = {1, 5, 10, 20, 40, 80,100};
		int	ar2[] = {6, 7, 20, 80, 100};
		int ar3[] = {3, 4, 15, 20, 30, 70, 80,100, 120};
		
		findCommonElements2(ar1, ar2, ar3);
	}
	
	public void findCommonElements(int[] ar1, int[] ar2, int[] ar3) {


	    for(int i=0; i<ar1.length; i++) {
	    
	        for(int j=0; j<ar2.length; j++) {
	       
	           if(ar2[j] > ar1[i])
	            {
	                break;
	            } else if(ar2[j] == ar1[i]) {
	                for(int k=0; k<ar3.length; k++) {
	                                    
	                    if(ar3[k] > ar2[j])
	                    {
	                        break;
	                    } else if(ar3[k] == ar2[j]) {
	                        System.out.println(ar1[i]);
	                        break;
	                    }
	                    
	                }
	            }
	            
	                
	        }
	    }
	}
	
	/*
	1.  Given three arrays sorted in increasing order, print all common elements in these arrays.

	Example 
	ar1[] = [1, 5, 10, 20, 40, 80]
	ar2[] = [6, 7, 20, 80, 100]
	ar3[] = [3, 4, 15, 20, 30, 70, 80, 120]
	Output: 20, 80
	*/
	public static void findCommonElements2(int[] ar1, int[] ar2, int[] ar3) {
		int j=0,k=0;
		
		for(int i=0; i<ar1.length;) {
			
			if(ar2[j]==ar1[i]) {
				if(ar2[j]==ar3[k]) {
					System.out.println(ar1[i]);
					i++;
					j++;
					k++;
				} else if(ar3[k] > ar2[j]) {
					j++;
				} else {
					k++;
				}
			} else if(ar2[j] > ar1[i]) {
				i++;
			} else {
				j++;
			}
			
		}
		
	}
}
