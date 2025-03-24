package prelims;

import java.util.Arrays;

public class O2PartitionArrayWithPivot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 // Test Case 1: A basic example
        int[] arr1 = {9, 3, 4, 2, 5, 6};
        int pivot1 = 5;
        int pivotIndex1 = partitionArray(arr1, pivot1);
        System.out.println("Test Case 1: " + Arrays.toString(arr1) + " | Pivot index: " + pivotIndex1);
        // Expected: Elements < 5 on the left, >= 5 on the right.
        // One possible output: [3, 4, 2, 5, 9, 6] with pivot index at 3.
        
        // Test Case 2: Another example with different numbers
        int[] arr2 = {4, 1, 3, 8, 7, 2, 5};
        int pivot2 = 4;
        int pivotIndex2 = partitionArray(arr2, pivot2);
        System.out.println("Test Case 2: " + Arrays.toString(arr2) + " | Pivot index: " + pivotIndex2);
        // Expected: Elements < 4 on the left, >= 4 on the right.
        // One possible output: [1, 3, 2, 4, 8, 7, 5] with pivot index at 3.
        
        // Test Case 3: An array with descending order
        int[] arr3 = {10, 9, 8, 7, 6};
        int pivot3 = 7;
        int pivotIndex3 = partitionArray(arr3, pivot3);
        System.out.println("Test Case 3: " + Arrays.toString(arr3) + " | Pivot index: " + pivotIndex3);
        // Expected: Elements < 7 on the left, >= 7 on the right.
        // One possible output: [6, 7, 8, 9, 10] with pivot index at 1.
    }
	
	public static int partitionArray(int[] arr, int pivot) {
		
		int res[]=new int[arr.length];
		int index=0;
		
		//first finding less than equals element
		for(int i=0;i<arr.length;i++) {
			if(arr[i]<pivot) {
				res[index]=arr[i];
				index++;
			}
			
		}
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==pivot) {
				res[index]=arr[i];
				index++;
			}
			
		}
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>pivot) {
				res[index]=arr[i];
				index++;
			}
		}
		for(int i=0;i<arr.length;i++) {
			if(res[i]==pivot) {
				return i;
			}
		}
		return -1;
	}
		 
	}

