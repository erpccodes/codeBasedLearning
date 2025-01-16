package practice;

public class HCFOfArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arr[] = {12, 15, 27};
		int a=arr[0];
		for(int i=1;i<arr.length;i++) {
			a=HCFOf(a,arr[i]);
			
		}
		
		System.out.println("HCF of all the array elements is: "+a);
	}
	
	public static int HCFOf(int a,int b) {
		while(b!=0) {
			int temp=b;
			b=a%b;
			a=temp;
		}
		return a;
	}

}
