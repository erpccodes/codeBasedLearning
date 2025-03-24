package prelims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Merge Two Sorted Arrays directly correlates to the merge step in Merge Sort.

public class O1MergeTwoSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test Case 1: Equal Length Arrays
        int[] arr1_1 = {1, 3, 5};
        int[] arr1_2 = {2, 4, 6};
        int[] merged1 = mergeSortedArrays(arr1_1, arr1_2);
        System.out.println("Test Case 1: " + Arrays.toString(merged1));
        // Expected Output: [1, 2, 3, 4, 5, 6]

        // Test Case 2: One Array is Empty
        int[] arr2_1 = {1, 2, 3};
        int[] arr2_2 = {};
        int[] merged2 = mergeSortedArrays(arr2_1, arr2_2);
        System.out.println("Test Case 2: " + Arrays.toString(merged2));
        // Expected Output: [1, 2, 3]

        // Test Case 3: The Other Array is Empty
        int[] arr3_1 = {};
        int[] arr3_2 = {4, 5, 6};
        int[] merged3 = mergeSortedArrays(arr3_1, arr3_2);
        System.out.println("Test Case 3: " + Arrays.toString(merged3));
        // Expected Output: [4, 5, 6]

        // Test Case 4: Arrays with Duplicate Values
        int[] arr4_1 = {1, 1, 2};
        int[] arr4_2 = {1, 3, 4};
        int[] merged4 = mergeSortedArrays(arr4_1, arr4_2);
        System.out.println("Test Case 4: " + Arrays.toString(merged4));
        // Expected Output: [1, 1, 1, 2, 3, 4]

        // Test Case 5: Arrays of Different Lengths
        int[] arr5_1 = {1, 3, 5, 7};
        int[] arr5_2 = {2, 4, 6, 8, 10};
        int[] merged5 = mergeSortedArrays(arr5_1, arr5_2);
        System.out.println("Test Case 5: " + Arrays.toString(merged5));
        // Expected Output: [1, 2, 3, 4, 5, 6, 7, 8, 10]
    }
	
	// Function to merge two sorted arrays and return a merged sorted array.
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
       
    	List<Integer> list=new ArrayList<>();
    	int l=0,r=0;
    	
    	while(l<arr1.length && r<arr2.length) {
    		if(arr1[l]<=arr2[r]) {
    			list.add(arr1[l]);
    			l++;
    		}else
    		{
    			list.add(arr2[r]);
    			r++;
    		}
    	}
    	
    	while(l<arr1.length) {
    		list.add(arr1[l]);
    		l++;
    	}
    	while(r<arr2.length) {
    		list.add(arr2[r]);
    		r++;
    	}
    	return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
	
	}
