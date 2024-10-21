package string_vs_stringbuilder_vs_stringbuffer;


/*
 * Since String is immutable, once a String object is created, it cannot be changed. 
 * Any operation that modifies a String actually creates a new String object instead of 
 * modifying the original one. This immutability ensures that even if multiple threads are 
 * accessing and "modifying" a string, they are actually working with separate objects, 
 * which eliminates race conditions.
 * 
 */

public class StringImmutability {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 String sharedString = "Hello";
		 
		 // Create two threads that attempt to modify the string
        Thread thread1 = new Thread(() -> {
            String newString = sharedString.concat(" from Thread 1");
            System.out.println(newString);
        });

        Thread thread2 = new Thread(() -> {
            String newString = sharedString.concat(" from Thread 2");
            System.out.println(newString);
        });

        // Start both threads
        thread1.start();
        thread2.start();
        // this can print before or after the thread as timing is not fixed.
        System.out.println(sharedString);
        
    }
	}


/* 
 * Thread execution timing is non-deterministic: 
 * When you call thread1.start() and thread2.start(), you're only requesting that 
 * the threads start. The actual start time of the threads is controlled by the operating system's 
 * thread scheduler, and there’s no guarantee which will run first or how quickly they will start.
 * 
 * 
 * 
 */