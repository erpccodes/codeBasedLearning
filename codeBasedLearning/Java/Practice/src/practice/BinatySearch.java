package practice;

public class BinatySearch {
	public static void main(String args[]) {
	int[] arr3 = {10, 20, 30, 40, 50};
	System.out.println(binarySearch(arr3, 20, 0, arr3.length - 1));
	
}

public static int binarySearch(int[] arr, int target,int low,int high) {
	while(low<=high) {
		
		// Avoid integer overflow
		int mid=low + (high - low) / 2;
		if(arr[mid]>target) {
			high=mid-1;
		}else if(arr[mid]<target) {
			low=mid+1;
		}else return mid;
	}
	return -1;
}

}
