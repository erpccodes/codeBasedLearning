package practice;

// Finding HCF / GCD of two Numbers
// using Euclid's Algorithm i.e.    GCD(a,b)=GCD(b,a%b)
public class HCF_GCDOfTwoNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=96,b=56;
		
		while(a!=0) {
			int temp=a;
			a=b%a;
			b=temp;
		}
		System.out.println("HCF is: "+b);
	}

}
