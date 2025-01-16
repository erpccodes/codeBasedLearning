package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice {
	
	public static void main(String args[]) {
		
		int arr[][]= {{3,2,1},{2,1,4},{0,5,8},{0,5,8}};
		
		for(int[] row:arr) {
			System.out.println(Arrays.toString(row));
		}
		
		int m=arr[0].length;
		int n=arr.length;
		System.out.println(m);
		System.out.println(n);
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				
			}
		}
		
	}

}
