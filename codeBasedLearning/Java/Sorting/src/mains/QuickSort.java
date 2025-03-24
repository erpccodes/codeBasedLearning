package mains;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // Test Case 1: Unsorted array
        int[] arr1 = {5, 2, 8, 1, 9};
        System.out.println("Original arr1: " + Arrays.toString(arr1));
        quickSort(arr1, 0, arr1.length - 1);
        System.out.println("Sorted arr1:   " + Arrays.toString(arr1));
        System.out.println();

        // Test Case 2: Already sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Original arr2: " + Arrays.toString(arr2));
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println("Sorted arr2:   " + Arrays.toString(arr2));
        System.out.println();

        // Test Case 3: Reverse sorted array
        int[] arr3 = {9, 8, 7, 6, 5};
        System.out.println("Original arr3: " + Arrays.toString(arr3));
        quickSort(arr3, 0, arr3.length - 1);
        System.out.println("Sorted arr3:   " + Arrays.toString(arr3));
        System.out.println();

        // Test Case 4: Array with duplicates
        int[] arr4 = {4, 2, 4, 1, 2, 5};
        System.out.println("Original arr4: " + Arrays.toString(arr4));
        quickSort(arr4, 0, arr4.length - 1);
        System.out.println("Sorted arr4:   " + Arrays.toString(arr4));
        System.out.println();

        // Test Case 5: Single element array
        int[] arr5 = {10};
        System.out.println("Original arr5: " + Arrays.toString(arr5));
        quickSort(arr5, 0, arr5.length - 1);
        System.out.println("Sorted arr5:   " + Arrays.toString(arr5));
        System.out.println();

        // Test Case 6: Empty array
        int[] arr6 = {};
        System.out.println("Original arr6: " + Arrays.toString(arr6));
        quickSort(arr6, 0, arr6.length - 1);
        System.out.println("Sorted arr6:   " + Arrays.toString(arr6));
	}

	
	private static void quickSort(int[] arr, int left, int right) {
		
		if(left>=right)
			return;
			
			int pivot=arr[left];
			int pivotIndex=placePivot(arr,left,right,pivot);
			quickSort(arr,left,pivotIndex-1);
			quickSort(arr, pivotIndex+1, right);
	}


	private static int placePivot(int[] arr, int i, int j,int pivot) {
		while(i<j) {
			while(pivot>arr[i])
				i++;
			while(pivot<arr[j])
				j--;
			
			 // If pointers have met or crossed, partitioning is complete
	        if (i >= j) {
	            return j;
	        }
	        swap(arr,i,j);
	        i++;
	        j--;
		}
		
       
		
		return j;
	}


	private static void swap(int[] arr, int i, int j) {
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
		
	}
	
	

}
