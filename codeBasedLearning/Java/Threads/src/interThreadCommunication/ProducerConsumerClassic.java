package interThreadCommunication;

public class ProducerConsumerClassic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProducerConsumerClassic buffer = new ProducerConsumerClassic();

		Thread producer = new Thread(() -> {
		    try {
		        buffer.produce();
		    } catch (Exception e) {}
		});

		Thread consumer = new Thread(() -> {
		    try {
		        buffer.consume();
		    } catch (Exception e) {}
		});

		producer.start();
		consumer.start();

}
	
	
	 private boolean available = false;

	    public synchronized void produce() throws InterruptedException {
	    	System.out.println("Inside producer");
	        while (available) {
	            wait();
	        }
	        Thread.sleep(1000);
	        System.out.println("Produced item");

	        available = true;

	        notify();
	    }

	    public synchronized void consume() throws InterruptedException {
	    	System.out.println("Inside Consumer");
	        while (!available) {
	            wait();
	        }

	        System.out.println("Consumed item");

	        available = false;

	        notify();
	    }
}
