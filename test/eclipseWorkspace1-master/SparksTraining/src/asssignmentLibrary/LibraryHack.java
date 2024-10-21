package asssignmentLibrary;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class LibraryHack {

		
		public static void main(String args[]) throws Exception {
			Scanner sc=new Scanner(System.in);
			System.out.println("............................Welcome to Library Management System..........................");
			System.out.println("\n");
			
			System.out.println("Enter 1 for viewing all the books in library");
			System.out.println("Enter 2 for borrow a new book from the library");
			System.out.println("Enter 3 for viewing members details");
			System.out.println("Enter 4 for returning the book");
			System.out.println("Enter 5 for exiting from the system");
		
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Library_Management","root", "Prince@9928");
			int choice=0;
			while(choice!=5) {
				System.out.println("Please enter the choice");
				choice=sc.nextInt();
				
			switch(choice) {
			case 1:
				Library li=new Library();
				li.showAllBooks();
				break;
				
			case 2:
				MemberAccount ma2=new MemberAccount();
				ma2.borrowBooks();
				break;
				

			case 3:
				MemberAccount ma3=new MemberAccount();
				ma3.memberdetails();
				break;
				
			case 4:
				MemberAccount ma=new MemberAccount();
				System.out.println("Please enter the ISBN Number of a book which you want to remove");
			
				System.out.println(ma.returnBooks(sc.next()));
				break;
				
			case 5:
				System.out.println("Thank you for using the system");
				System.exit(choice);
				break;
			}
			}


	}

}
