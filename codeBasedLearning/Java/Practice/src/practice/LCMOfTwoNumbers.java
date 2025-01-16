package practice;

// To Calculate LCM of two Numbers 
// LCM(a,b)= (a*b)/HCF(a,b)
public class LCMOfTwoNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a=156,b=96;
		
		System.out.print("LCM of the two numbers is: "+LCMof(a,b));

	}
	
	public static int LCMof(int a, int b) {
		int LCM=(a*b)/(HCFof(a,b));
		return LCM;
	}
	
	public static int HCFof(int a,int b) {
		while(b!=0)
		{
			int temp=b;
			b=a%b;
			a=temp;
		}
		return a;
	}

}
