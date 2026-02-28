package basics;

public class ThreadUsingRunnable implements Runnable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread t1=new Thread(new ThreadUsingRunnable());
		t1.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Running thread: "+Thread.currentThread().getName()+ " -->Using Runnable");
	}

}
