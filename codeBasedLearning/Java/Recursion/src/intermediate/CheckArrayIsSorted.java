package intermediate;
// To test is array is sorted in increasing order
public class CheckArrayIsSorted {

	public static void main(String[] args) {
		 int[] arr1 = {1, 2, 3, 4, 5};
	        System.out.println(checkSortedArray(arr1, arr1.length));  // Expected Output: true

	        int[] arr2 = {10, 20, 30, 25};
	        System.out.println(checkSortedArray(arr2, arr2.length));  // Expected Output: false

	        int[] arr3 = {5};
	        System.out.println(checkSortedArray(arr3, arr3.length));  // Expected Output: true

	        int[] arr4 = {};
	        System.out.println(checkSortedArray(arr4, arr4.length));  // Expected Output: true (empty array is considered sorted)

	        int[] arr5 = {1, 1, 2, 2, 3, 4, 4};
	        System.out.println(checkSortedArray(arr5, arr5.length));  // Expected Output: true
	}
	
	public static boolean checkSortedArray(int arr[], int n) {
		if(n<=1) {
			return true;
		}
		if(arr[n-1]<arr[n-2]) {
			return false;
		}
		return checkSortedArray(arr,n-1);
	}

}
