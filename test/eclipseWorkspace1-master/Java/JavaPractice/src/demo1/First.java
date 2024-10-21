package demo1;

import java.util.ArrayList;
import java.util.List;

public class First {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]={2,4,5,7};
		int count=0;
		List<Integer> list=new ArrayList<Integer>();
		int i=0;
		while(i<arr.length) {
			int n=arr[i];
		for(int j=2;j<=n;j++) {
			if(n%j==0) count++;
		}
		if(count<0) list.add(n);
	}
	System.out.println(list);	
	}
}
