package intermediate;

public class O4BinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr1 = {1, 3, 5, 7, 9};
		System.out.println(binarySearch(arr1, 5, 0, arr1.length - 1)); // Output: 2

		int[] arr2 = {2, 4, 6, 8, 10};
		System.out.println(binarySearch(arr2, 8, 0, arr2.length - 1)); // Output: 3

		int[] arr3 = {10, 20, 30, 40, 50};
		System.out.println(binarySearch(arr3, 25, 0, arr3.length - 1)); // Output: -1

		int[] arr4 = {1, 2, 3, 4, 5};
		System.out.println(binarySearch(arr4, 1, 0, arr4.length - 1)); // Output: 0

		int[] arr5 = {100, 200, 300};
		System.out.println(binarySearch(arr5, 400, 0, arr5.length - 1)); // Output: -1
	}
	
	public static int binarySearch(int[] arr,int target,int low,int high) {
		if(low>=high)
			return -1;
		
		int mid=low+(high-low)/2;
		
		if(arr[mid]>target)
			return binarySearch(arr,target,low,mid-1);
		else if(arr[mid]<target)
			return binarySearch(arr,target,mid+1,high);
		else 
			return mid;
	}

}
