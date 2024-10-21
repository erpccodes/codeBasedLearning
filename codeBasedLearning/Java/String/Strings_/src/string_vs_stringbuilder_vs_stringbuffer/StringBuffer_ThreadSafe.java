/*
 * Problem:
You need to build a log message in a multi-threaded environment where 
multiple threads may append information to the same log entry.
 */

package string_vs_stringbuilder_vs_stringbuffer;

public class StringBuffer_ThreadSafe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 StringBuffer logMessage = new StringBuffer("Log Start: ");
	        
	        // Simulate multiple threads appending log entries
	        Thread thread1 = new Thread(() -> {
	            synchronized (logMessage) {
	                logMessage.append("[Thread1: Entry1] ");
	            }
	        });
	        
	        Thread thread2 = new Thread(() -> {
	            synchronized (logMessage) {
	                logMessage.append("[Thread2: Entry2] ");
	            }
	        });
	        
	        // Start both threads
	        thread1.start();
	        thread2.start();
	        
	        try {
	            thread1.join();
	            thread2.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        // Output the final log message
	        System.out.println(logMessage.toString());
	    }
	}

/*  
 * All methods in StringBuffer are synchronized, meaning only one thread can 
 * access a StringBuffer object at a time.
 * 
 * When one thread is modifying the StringBuffer,
 *  other threads must wait until the current thread finishes, 
 *  which ensures that no two threads can modify the same object at the same time. 
 *  This makes it safe for use in multi-threaded environments.
 *  
 *  
 */