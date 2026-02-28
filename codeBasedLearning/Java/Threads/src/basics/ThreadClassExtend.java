package basics;

public class ThreadClassExtend extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread running at run: "+Thread.currentThread().getName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadClassExtend t1= new ThreadClassExtend();
		System.out.println("Thread on main: "+t1.currentThread().getName()); 
		t1.start();
		
	}

}
