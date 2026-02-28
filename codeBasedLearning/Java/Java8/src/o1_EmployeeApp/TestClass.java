package o1_EmployeeApp;

public class TestClass implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Test");
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		



		TestClass task = new TestClass();

		
		Thread thread =new Thread(task);
		thread.start();
	}



}
