package basics;

public class RunnableUsingLambda{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable t1=()->{
			System.out.println("Running thread using Lambda Implementaion with thread : "
		+Thread.currentThread().getName());
		};
		
		new Thread(t1).start();
		
	}


}
