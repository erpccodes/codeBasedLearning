package Basic;

public class O3Fibonaci {
	
	public static void main(String[] args) {
		fib(0,1,5);
	}

	public static void fib(int previous,int current,int n) {
		if(previous>n)
			return;
		System.out.println(previous);
		fib(current,previous+current,n);
	}
}
