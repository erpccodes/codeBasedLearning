package array;
import java.util.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class One {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		int count=0;
		int[] arr=new int[num];
		for(int i=0;i<num;i++) {
		arr[i]=sc.nextInt();
		}
		
	for(int i=0;i<num;i++) {
		int[] arr2=new int[i+1];
		for(int j=0;j<arr2.length;j++) {
			arr2[j]=arr[j];
		}
		int last=arr2[arr2.length-1];
		
		for(int k=0;k<arr2.length;k++) {
			if(arr2[k]<=last)
				count++;
		}
		System.out.println(count);
		count=0;
	}
	
	}
}

