package basics;

public class FirstClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1="Hello";
		String str2="Hello";
		String str3=new String("Hello");		// Memory always gets stored in Heap Memory
		
		System.out.println(str1 == str2); // true - Both refer to the same object in the string pool.
		System.out.println(str1 == str3); // false - str3 refers to a different object in the heap.
		
		
		System.out.println(str2.equals(str3));	// equals method only compare values
		String str4=new String("Hello").intern(); // intern method Check if String is present in Stringpool and Put the string object in string pool
		System.out.println(str1 == str4);		// true since Hello is already present in string pool
		
	System.out.println("------------------------------------");
	
	
	}

}
