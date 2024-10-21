package array;

import java.util.Scanner;

public class Two { 
                     
	public static void main(String args[]) { 
		             Scanner sc=new Scanner(System.in);
		             int n=sc.nextInt();
		             int div=sc.nextInt();
		             int sum=0;
		             for(int i=0;i<n;i++) {
		            	 int num=sc.nextInt();
		            	 sum+=num/div;
		             }
		             System.out.println(sum);
		             
		 
	} 
}


