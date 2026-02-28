package lifecycle;

public class ThreadLifeCycle {

		// TODO Auto-generated method stub

		public static void main(String[] args) throws InterruptedException {

			System.out.println(Thread.currentThread().getName());
	        Thread t = new Thread(() -> {
	            System.out.println("Thread started");
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {}
	            System.out.println("Thread ended");
	        });
	       
	        System.out.println("State after creation: " + t.getState()+ "; thread name:"+t.getName());

	        t.start();
	        System.out.println("State after start(): " + t.getState()+ "; thread name:"+t.getName());

	        Thread.sleep(500);
	        System.out.println("State during sleep: " + t.getState()+ "; thread name:"+t.getName());

	       // t.join();
	        System.out.println("State after completion: " + t.getState()+ "; thread name:"+t.getName());
	    
	}
	
}
