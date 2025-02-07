package Basic;

// Print number 1-N
public class o1Recursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N=5,count=1;
		printNum(N,count);
	}
	
	public static void printNum(int N,int count) {
		if(count>N)			// base condition
			return;
		System.out.println(count);
		printNum(N,count+1);   // Recursive condition
	}

}
