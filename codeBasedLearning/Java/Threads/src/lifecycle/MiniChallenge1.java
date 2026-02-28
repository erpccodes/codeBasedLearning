package lifecycle;

/*
 * 
 * Create 2 threads

First thread sleeps for 2 seconds

Second thread waits using join()

Print execution order
 * 
 * 
 */

public class MiniChallenge1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub\
			Thread t1=new Thread(()-> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("running thread 1");
				});
			
			Thread t2=new Thread(()-> {
				try {
					t1.join(); // t2 waits for t1
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("running thread 2");});
			t1.start();
			t2.start();

			
	}

}


/*
 * 
 * 
 * 
 * join() causes the calling thread to wait until the target thread finishes execution.

✔ t2.join() → main waits for t2
✔ t1.join() inside t2 → t2 waits for t1
*/