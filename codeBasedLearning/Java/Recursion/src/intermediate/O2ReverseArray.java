package intermediate;

public class O2ReverseArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int[] arr = {1, 2, 3, 4, 5};
		printArrayReverse(arr, arr.length - 1);
		
	}
	
	public static void printArrayReverse(int[] arr,int n) {
		
		if(n<0) {
			return;
		}
		System.out.println(arr[n]);
		printArrayReverse(arr, n-1);
	}

}
