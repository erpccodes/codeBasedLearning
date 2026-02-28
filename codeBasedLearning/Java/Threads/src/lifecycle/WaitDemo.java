package lifecycle;


class SharedResource {

    private boolean dataAvailable = false;

    public synchronized void consume() {
        try {
            while (!dataAvailable) {
                System.out.println("Consumer waiting...");
                wait();   // releases lock and waits
            }

            System.out.println("Consumer consumed data");
            dataAvailable = false;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void produce() {
        System.out.println("Producer producing data...");
        dataAvailable = true;

        notify();  // wakes up waiting thread
    }
}

public class WaitDemo {

    public static void main(String[] args) {

        SharedResource resource = new SharedResource();

        Thread consumer = new Thread(() -> {
            resource.consume();
        });
        
        System.out.println("Consumer thread: "+consumer.getName());

        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            resource.produce();
        });

        System.out.println("producer thread: "+producer.getName());
        
        consumer.start();
        producer.start();
    }
}