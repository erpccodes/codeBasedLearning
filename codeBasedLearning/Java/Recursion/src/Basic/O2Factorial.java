package Basic;

import java.util.Scanner;

public class O2Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the digit to find its factorial");
		int n=sc.nextInt();
		
		System.out.println(fact(n));
		
	}
	
	public static int fact(int n) {
		if(n<=1)
			return 1;
		int fact=n*fact(n-1);
		return fact;
		
	}

}
